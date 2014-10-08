package java.text;

import java.io.Serializable;
import java.text.ParseException;

/** 
 * A stub for providing this feature.  Mark it so it does not use this type for the DynType wrapper used when reflection is not available.  
 * In GWT, the runtime for development does not permit changing this particular class.
 */
@CompilerSettings(useExternalDynType=true)
public class DecimalFormat extends NumberFormat
{
   public DecimalFormat() {
      super();
   }

   public void applyPattern(String pattern) {
      applyFormat(com.google.gwt.i18n.client.NumberFormat.getFormat(pattern));
   }

   public DecimalFormat(String pattern) {
      applyPattern(pattern);
   }
}
