import sc.dyn.DynUtil;
import sc.dyn.IDynObjManager;
import sc.type.CTypeUtil;
import sc.wicket.CWebPage;
import org.apache.wicket.Component;
import org.apache.wicket.Application;

public class WicketDynObjManager implements IDynObjManager {

   /** Wicket components use the object's type name as the wicket id if it is a Component class. */
   public Object createInstance(Object type, Object parentInst, Object[] args) {
      String typeName = CTypeUtil.decapitalizePropertyName(CTypeUtil.getClassName(DynUtil.getTypeName(type, false)));

      Object newType = type;
      
      if (parentInst != null && parentInst instanceof Component) {
         Component comp = (Component) parentInst;
         Page p = comp instanceof Page ? (Page) comp : comp.findParent(Page.class);
         if (p instanceof CWebPage) {
            Application theApp = ((CWebPage) p).explicitApplication;
            if (theApp != null)
               Application.set(theApp);
         }
      }
      if (DynUtil.isAssignableFrom(Component.class, newType) && !DynUtil.isAssignableFrom(Page.class, newType)) {
         if (args == null)
            args = new Object[0];
         Object[] newArgs = new Object[args.length + 1];
         System.arraycopy(args, 0, newArgs, 0, args.length);
         newArgs[args.length] = typeName;
         args = newArgs;
      }
      if (parentInst != null)
         return DynUtil.createInnerInstance(newType, parentInst, null, args);
      else
         return DynUtil.createInstance(newType, null, args);
   }
}
