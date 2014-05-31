import sc.bind.IListener;
import sc.bind.Bind;
import sc.dyn.DynUtil;

@CompilerSettings(propagateConstructor="")
class CListItem<T> extends ListItem<T> {
   // Stores any scope<ListItem> children associated with this list item
   public Object[] children;

   public CListItem(final int index, final IModel<T> model) {
      super(index, model);
   }

   public void sendChangeEvents() {
      if (children == null)
         return;

      int sz = children.length;
      for (int i = 0; i < sz; i++) {
         Object child = children[i];
         if (DynUtil.isAssignableFrom(IListItemScope.class, DynUtil.getType(child))) {
            Bind.sendDynamicEvent(IListener.VALUE_CHANGED, child, "listItemValue");
         }
      }
   }
}
