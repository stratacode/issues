import sc.bind.IListener;
import sc.bind.Bind;
import org.apache.wicket.Component;
import org.apache.wicket.Application;

class CWebPage extends WebPage {
   /** Stores the app for this page.  Wicket likes to use thread local for the current 
    * application which does not work when we need to create a new component from the 
    * command-line or some other non-servlet thread.  To fix this, we stash the existing app
    * here at construction.  When the child component is created we get the page and then the
    * app and populate it in the thread. 
    */
   transient Application explicitApplication;

   public CWebPage() {
      explicitApplication = Application.get();
   }
/*
   private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException {
      s.defaultReadObject();

      IComponent comp = (IComponent) this;
      comp.preInit();
      comp.init();
      comp.start();
      // Fields all change but no events fired on deserialize.  This will take care of that for us.  It seems better to wait for
      // the graph to be fully set up before sending all of the events.  Otherwise, we could do it in each components readObject method. 
      // TODO: do we need to handle non-wicket components here?
      Bind.sendAllEvents(IListener.VALUE_CHANGED, this);
      visitChildren(new Component.IVisitor<Component>() {
         public Object component(final Component component) {
             Bind.sendAllEvents(IListener.VALUE_CHANGED, component);
             return IVisitor.CONTINUE_TRAVERSAL;
         }
      });
   }
   */

}
