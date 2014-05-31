import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;

public class CTextBox extends TextBox implements KeyboardListener {
   private java.util.List _items;

   override @Bindable text;

   {
      addKeyboardListener(this);
   }

   public void onKeyDown(Widget sender, char keyCode, int modifiers) {}

   public void onKeyPress(Widget sender, char keyCode, int modifiers) {}

   public void onKeyUp(Widget sender, char keyCode, int modifiers) {
      setText(getText());
   }
}
