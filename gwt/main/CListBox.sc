import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;

import com.google.gwt.user.client.ui.ListBox;

public class CListBox extends ListBox implements ChangeHandler {
   private java.util.List _items;

   override @Bindable selectedIndex;

   public void setItems(java.util.List newItems) {
      _items = newItems;
      clear();
      for (Object item:_items) {
         addItem(item.toString());
      }
   }

   public java.util.List getItems() {
      return _items;
   }

   {
      addChangeHandler(this);
   }

   public void onChange(ChangeEvent sender) {
      // Fire the change event
      setSelectedIndex(getSelectedIndex());
   }
}
