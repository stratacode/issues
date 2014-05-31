import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;

import sc.util.StringUtil;

public final class GuestBook extends WebPage {
   /** A global list of all comments from all users across all sessions */
   private static final List<Comment> commentList = new ArrayList<Comment>();

   object commentForm extends Form<ValueMap> {
      markupId = "commentForm";

      defaultModel = new CompoundPropertyModel<ValueMap>(new ValueMap());

      object text extends TextArea<String> {
         type = String.class;
      }
      object comment extends TextArea<String> {
         type = String.class;
      }

      /**
       * Show the resulting valid edit
       */
      @Override
      public final void onSubmit() {
         ValueMap values = getModelObject();

         // check if the honey pot is filled
         if (!StringUtil.isBlank((String)values.get("comment"))) {
             error("Caught a spammer!!!");
             return;
         }
         // Construct a copy of the edited comment
         Comment comment = new Comment();

         comment.text = (String)values.get("text");
         commentList.add(0, comment);

         // Clear out the text component
         values.put("text", "");
      }
   }

   object comments extends CListView<Comment> {
      defaultModel = Model.of(commentList);

      /*
      scope<ListItem> object date extends Label {
      }
      scope<ListItem> object text extends MultiLineLabel {
      }
      */

      scope<ListItem> object listItems {
         object date extends Label {
         }
         object text extends MultiLineLabel {
         }
      }

      versioned = false;
   }

   public GuestBook() {
   }

   /**
    * Clears the comments.
    */
   public static void clear() {
       commentList.clear();
   }
}
