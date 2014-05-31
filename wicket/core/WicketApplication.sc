import sc.bind.IListener;
import sc.bind.BindingContext;
import sc.dyn.DynUtil;
import sc.dyn.RDynUtil;
import org.apache.wicket.application.IComponentOnBeforeRenderListener;
import org.apache.wicket.protocol.http.*;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.request.IRequestCycleProcessor;

import java.util.Map;

@CompilerSettings(needsCompiledClass=true)
class WicketApplication extends WebApplication {
   private Class homePage;

   private static object statementProcessor extends WicketStatementProcessor {}

   object bindingManager extends WicketBindingManager {}

   public void setHomePage(Class hp) {
      homePage = hp;
   }
   public Class<? extends Page> getHomePage() {
      if (homePage == null)
         System.err.println("Error: must set WicketApplication.homePage to a class which extends WebPage");
      return homePage;
   }

   public static void queueEvents() {
      if (BindingContext.getBindingContext() != null) 
         throw new IllegalArgumentException("queueEvents called when it is already enabled");
      BindingContext ctx = new BindingContext();
      BindingContext.setBindingContext(ctx);
   }
   
   public static void flushEvents() {
      BindingContext ctx = BindingContext.getBindingContext(); 
      // Before we render any components, flush out any queued events and clear the context.
      if (ctx != null) {
         // Do this before dispatchEvents so we don't keep queueing them
         BindingContext.setBindingContext(null);
         ctx.dispatchEvents(null);
      }
   }

   protected void init() {
      RDynUtil.setSystemClassLoader(getClass().getClassLoader());
      bindingManager; // registers itself first time it is accessed

      ISessionSettings settings = getSessionSettings();
      settings.setPageFactory(new SCPageFactory());

      String[] dynSrcDirs = DynUtil.getDynSrcDirs();
      String[] dynSrcPrefixes = DynUtil.getDynSrcPrefixes();
      IResourceSettings resourceSettings = getResourceSettings();
      if (dynSrcDirs != null) {
         for (String srcDir:dynSrcDirs) {
            resourceSettings.addResourceFolder(srcDir);
         }
         resourceSettings.setResourceStreamLocator(new ResourceStreamLocator(dynSrcDirs, dynSrcPrefixes));
      }

      super.init();
      addPreComponentOnBeforeRenderListener(new IComponentOnBeforeRenderListener() {
         public void onBeforeRender(org.apache.wicket.Component component) {
            flushEvents();
         }
      });
   }

   public RequestCycle newRequestCycle(final Request request, final Response response) {
       return new SCRequestCycle(this, (WebRequest)request, (WebResponse)response);
   }
 
   public AjaxRequestTarget newAjaxRequestTarget(final Page page) {
      AjaxRequestTarget at = super.newAjaxRequestTarget(page);

      at.addListener(new AjaxRequestTarget.IListener() {
        public void onBeforeRespond(Map<String, org.apache.wicket.Component> map, AjaxRequestTarget target) {
            flushEvents();
        }
        public void onAfterRespond(Map<String, org.apache.wicket.Component> map, AjaxRequestTarget.IJavascriptResponse response) {
        }
      });
      return at;
   }

 
   protected IRequestCycleProcessor newRequestCycleProcessor()
   {
      return new SCRequestCycleProcessor();
   }
}
