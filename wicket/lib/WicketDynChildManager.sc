import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Component;
import java.util.Iterator;
import java.util.ArrayList;

public class WicketDynChildManager implements sc.dyn.IDynChildManager {

   public void initChildren(Object parentObj, Object[] children) {
      if (parentObj instanceof MarkupContainer) {
         MarkupContainer parent = (MarkupContainer) parentObj;

         for (Object childObj:children) {
            if (childObj instanceof Page)
               continue;

            if (childObj instanceof Component) {
               Component child = (Component) childObj;
               parent.add(child);
            }
            if (childObj instanceof IBehavior) {
               IBehavior child = (IBehavior) childObj;
               parent.add(child);
            }
         }
      }
      else if (parentObj instanceof Component) {
         Component parent = (Component) parentObj;

         for (Object childObj:children) {
            if (childObj instanceof IBehavior) {
               IBehavior child = (IBehavior) childObj;
               parent.add(child);
            }
         }
      }
   }

   public void addChild(Object parentObj, Object childObj) {
      addChild(-1, parentObj, childObj);
   }

   public void addChild(int ix, Object parentObj, Object childObj) {
      if (parentObj instanceof MarkupContainer) {
         MarkupContainer parent = (MarkupContainer) parentObj;
         if (childObj instanceof Component) {
            // TODO: how do we deal with ix here? 
            parent.add((Component) childObj);
         }
         else if (childObj instanceof IBehavior) {
            parent.add((IBehavior) childObj);
         }
      }
      else if (parentObj instanceof Component) {
         Component parent = (Component) parentObj;
         if (childObj instanceof IBehavior) {
            parent.add((IBehavior) childObj);
         }

      }
      else
         System.err.println("*** Unrecognized parent: " + parentObj);
   }

   public boolean removeChild(Object parentObj, Object childObj) {
      if (parentObj instanceof MarkupContainer) {
         MarkupContainer parent = (MarkupContainer) parentObj;
         if (childObj instanceof Component) {
            parent.remove((Component) childObj);
         }
         else if (childObj instanceof IBehavior) {
            parent.remove((IBehavior) childObj);
         }
      }
      else if (parentObj instanceof Component) {
         Component parent = (Component) parentObj;
         if (childObj instanceof IBehavior) {
            parent.remove((IBehavior) childObj);
         }
      }
      else
         System.err.println("*** Unrecognized parent: " + parentObj);
      return false;
   }

   public Object[] getChildren(Object parentObj) {
      if (parentObj instanceof MarkupContainer) {
         MarkupContainer parent = (MarkupContainer) parentObj;

         ArrayList<Object> result = new ArrayList<Object>();
         Iterator children = parent.iterator();
         while (children.hasNext()) {
            result.add(children.next());
         }
         List<IBehavior> behaviors = parent.getBehaviors();
         result.addAll(behaviors);
         return result.toArray();
      }
      else if (parentObj instanceof Component) {
         Component parent = (Component) parentObj;
         return parent.getBehaviors().toArray();
      }
      else
         System.err.println("*** Unrecognized parent: " + parentObj);
      return null;
   }

   @Override
   public boolean getInitChildrenOnCreate() {
      return true;
   }
}
