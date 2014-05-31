import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.border.BoxBorder;

class NavomaticBorder extends Border {
   NavomaticBorder(final String componentName) {
      super(componentName);
      add(new BoxBorder("navigationBorder"));
      add(new BoxBorder("bodyBorder"));
   }
}
