import sc.bind.BindingContext;

import org.apache.wicket.protocol.http.WebRequestCycle;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.Component;

public class SCRequestCycle extends WebRequestCycle {

   static ThreadLocal<AjaxRequestTarget> currentAjaxRequestTarget = new ThreadLocal<AjaxRequestTarget>();
   static ThreadLocal<Component> currentRequestComponent = new ThreadLocal<Component>();

   public SCRequestCycle(final WebApplication application, final WebRequest request, final Response response) {
       super(application, request, response);
   }

   public static AjaxRequestTarget getCurrentAjaxRequestTarget() {
      return currentAjaxRequestTarget.get();
   }

   public static void setCurrentAjaxRequestTarget(AjaxRequestTarget t) {
      currentAjaxRequestTarget.set(t);
   }

   public static Component getCurrentRequestComponent() {
      return currentRequestComponent.get();
   }

   public static void setCurrentRequestComponent(Component t) {
      currentRequestComponent.set(t);
   }

   protected void onBeginRequest() {
       BindingContext ctx = BindingContext.getBindingContext(); 
       int numEvents;
       // Make sure to clear it out before the request
       setCurrentAjaxRequestTarget(null);

       if (ctx == null) {
          /*
           * Too broad a place to set this
          ctx = new BindingContext();
          BindingContext.setBindingContext(ctx);
          */
       }
       else if ((numEvents = ctx.getQueueSize()) != 0) {
          System.err.println("*** BindingContext has: " + numEvents + " at the start of the request");
       }
   }
}
