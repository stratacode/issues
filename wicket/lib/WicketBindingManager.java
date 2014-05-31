import sc.bind.Bind;
import sc.bind.BindingManager;
import sc.bind.BindingContext;
import sc.bind.IListener;
import sc.type.IBeanMapper;

public class WicketBindingManager extends BindingManager {
   public WicketBindingManager() {
      Bind.bindingManager = this;
   }

   public void sendEvent(IListener listener, int event, Object obj, IBeanMapper prop, Object eventDetail) {
      BindingContext ctx = BindingContext.getBindingContext();
      if (ctx != null)
         ctx.queueEvent(event, obj, prop, listener, eventDetail);
      else
         super.sendEvent(listener, event, obj, prop, eventDetail);
   }

   public IListener.SyncType getDefaultSyncType() {
      return IListener.SyncType.QUEUED;
   }
}
