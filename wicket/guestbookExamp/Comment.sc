import java.util.Date;

import org.apache.wicket.IClusterable;

public class Comment implements IClusterable {
   @Bindable
   public String text;
   @Bindable
   public Date date = new Date();
   /**
   * Constructor
   */
   public Comment() {}

   /**
   * Copy constructor
   * 
   * @param comment
   *            The comment to copy
   */
   public Comment(final Comment comment) {
       this.text = comment.text;
       this.date = comment.date;
   }

   /**
   * @see java.lang.Object#toString()
   */
   public String toString()
   {
          return "[Comment date = " + date + ", text = " + text + "]";
   }
}
