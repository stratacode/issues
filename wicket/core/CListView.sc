import sc.dyn.IObjChildren;
import org.apache.wicket.behavior.IBehavior;

import sc.bind.IListener;
import sc.bind.Bind;
import sc.type.TypeUtil;
import sc.type.IBeanMapper;
import sc.dyn.DynUtil;
import sc.dyn.IDynObject;

@Component
@CompilerSettings(propagateConstructor="java.lang.String", objectTemplate="sc.wicket.CListViewObjTemplate", newTemplate="sc.wicket.CListViewNewTemplate",
            mixinTemplate="sc.wicket.CListViewMixinTemplate", childTypeParameter="T",
            dynChildManager="sc.wicket.WicketDynChildManager",
            dynObjManager="sc.wicket.WicketDynObjManager", liveDynamicTypes=true)
public abstract class CListView<T> extends PropertyListView<T> {
   // By default wicket will replace items on each rendering but this seems like the wrong default for sc.
   reuseItems = true;

   @Bindable(manual=true)
   ListView<T> setList(List<? extends T> val) {
      ListView<T> ret = super.setList(val);
      listChanged();
      return ret;
   }

   public void listChanged() {
      WicketUtil.sendEvent(IListener.VALUE_CHANGED, this, listProp);
      // When the list changes, the values in the list also must change.  Maybe there's a way to do
      // this by hooking into the CompoundModel we use?  That might be more reliable.

      int numItems = getViewSize();
      for (int i = 0; i < numItems; i++) {
         ListItem<T> childLI = (ListItem<T>) get(i);
         if (childLI instanceof CListItem) {
            ((CListItem) childLI).sendChangeEvents();
         }
      }
   }

   protected void onBeforeRender() {
      int oldViewSize = getViewSize();
      super.onBeforeRender();

      // Need to mark the list as changed - maybe an item was added or removed from the list?   Ideally we'd get better granularity than this so we
      // avoid the extra events
      listChanged();
   }

   protected void processChildrenItems(Object[] children, ListItem<T> item, boolean isRoot) {
      if (isRoot) {
         CListItem<T> cItem = (CListItem<T>) item;
         cItem.children = children;
      }
      for (Object child:children) {
         if (child instanceof org.apache.wicket.Component)
            item.add((org.apache.wicket.Component) child);
         else if (child instanceof IBehavior)
            item.add((IBehavior) child);
         else if (child instanceof IObjChildren) {
            Object[] subChildren = ((IObjChildren) child).getObjChildren(true);
            processChildrenItems(subChildren, item, false);
         }
         else if (child instanceof IDynObject) {
            Object[] subChildren = DynUtil.getObjChildren(child, null, true);
            processChildrenItems(subChildren, item, false);
         }
      }
   }

   /** Defined in the templates set via compiler settings */
   protected void _populateChildren(final ListItem<T> item) {
   }

   protected void populateItem(final ListItem<T> item) {
      int listItemIndex = item.getIndex();
      IModel<T> model = item.getModel();

      // Adds any compiled children
      _populateChildren(item);

      // Now add any dynamic children
      Object thisType = DynUtil.getType(this);
      String[] objNames = DynUtil.getObjChildrenNames(thisType, "ListItem");
      if (objNames != null) {
         Object[] objTypes = DynUtil.getObjChildrenTypes(thisType, "ListItem");

         ArrayList<Object> children = new ArrayList<Object>(objNames.length);

         for (int i = 0; i < objNames.length; i++) {
            Object subType = objTypes[i];
            Object inst;

            Object encType;
           
            // If this type has an id for a parameter - pages do not do this since they are top-level
            if (subType instanceof Component && !(subType instanceof Page)) {
               inst = DynUtil.createInnerInstance(subType, this, null, objNames[i]);
            }
            else {
               inst = DynUtil.createInnerInstance(subType, this, null);
            }

            DynUtil.setProperty(inst, "listItemIndex", listItemIndex);
            DynUtil.setProperty(inst, "listItemModel", model);

            children.add(inst);
         }
         processChildrenItems(children.toArray(), item, true);
      }
   }


   protected ListItem<T> newItem(final int index) {
      return new CListItem<T>(index, getListItemModel(getModel(), index));
   }

   private static IBeanMapper listProp = TypeUtil.getPropertyMapping(CListView.class, "list");
   //private static IBeanMapper listItemValueProp = TypeUtil.getPropertyMapping(IListItemScope.class, "listItemValue");
}
