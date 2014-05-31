package sc.wicket;

import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.parser.XmlTag;
import org.apache.wicket.model.IComponentAssignedModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.portlet.PortletRequestContext;
import org.apache.wicket.util.value.IValueMap;
import org.apache.wicket.IClusterable;
import org.apache.wicket.RequestContext;
import org.apache.wicket.Component;

/**
 * Derived from AttributeModifier, allowing the "attribute" to be specified as a property, not a constructor
 * argument.
 */
public class CAttributeModifier extends AbstractBehavior implements IClusterable {
   /** Marker value to have an attribute without a value added. */
   public static final String VALUELESS_ATTRIBUTE_ADD = "VA_ADD";

   /** Marker value to have an attribute without a value removed. */
   public static final String VALUELESS_ATTRIBUTE_REMOVE = "VA_REMOVE";

   private static final long serialVersionUID = 1L;

   /** Whether to add the attribute if it is not an attribute in the markup. */
   private boolean addAttributeIfNotPresent = false;

   /** Attribute specification. */
   private String attribute;

   /** Modification information. */
   private boolean enabled = true;

   /** The pattern. */
   public String pattern = null;

   /** The model that is to be used for the replacement. */
   private final IModel<?> replaceModel;

   /** The value of the attribute to use */
   public Object attributeValue = null;

   /**
    * Create a new attribute modifier with the given attribute name and model to replace with. The
    * additional boolean flag specifies whether to add the attribute if it is not present.
    * 
    * @param attribute
    *            The attribute name to replace the value for
    * @param addAttributeIfNotPresent
    *            Whether to add the attribute if it is not present
    * @param replaceModel
    *            The model to replace the value with
    */
   public CAttributeModifier() {
      replaceModel = new PropertyModel(this, "attributeValue");
   }

   public final void detach(Component component) {
      if (replaceModel != null) {
         replaceModel.detach();
      }
   }

   public boolean getAddAttributeIfNotPresent() {
      return addAttributeIfNotPresent;
   }
   public void setAddAttributeIfNotPresent(boolean at) {
      addAttributeIfNotPresent = at;
   }

   public final void setAttribute(String attr) {
      attribute = attr;
   }

   public final String getAttribute() {
      return attribute;
   }

   @Deprecated
   public final boolean isEnabled() {
      return enabled;
   }

   public boolean isEnabled(Component component) {
      return enabled;
   }

   public final void onComponentTag(Component component, ComponentTag tag) {
      if (tag.getType() != XmlTag.CLOSE) {
         replaceAttributeValue(component, tag);
      }
   }

   /**
    * Checks the given component tag for an instance of the attribute to modify and if all criteria
    * are met then replace the value of this attribute with the value of the contained model
    * object.
    * 
    * @param component
    *            The component
    * @param tag
    *            The tag to replace the attribute value for
    */
   public final void replaceAttributeValue(final Component component, final ComponentTag tag) {
      if (isEnabled(component)) {
         if (attribute == null)
            throw new IllegalArgumentException("CAttributeModifier is missing value of required property: 'attribute' for: " + this);
         final IValueMap attributes = tag.getAttributes();
         final Object replacementValue = getReplacementOrNull(component);

         if (VALUELESS_ATTRIBUTE_ADD.equals(replacementValue)) {
            attributes.put(attribute, null);
         }
         else if (VALUELESS_ATTRIBUTE_REMOVE.equals(replacementValue)) {
            attributes.remove(attribute);
         }
         else {
            if (attributes.containsKey(attribute)) {
               final String value = toStringOrNull(attributes.get(attribute));
               if (pattern == null || value.matches(pattern)) {
                  final String newValue = newValue(value, toStringOrNull(replacementValue));
                  if (newValue != null) {
                     attributes.put(attribute, getContextRelativeValue(newValue));
                  }
               }
            }
            else if (addAttributeIfNotPresent) {
               final String newValue = newValue(null, toStringOrNull(replacementValue));
               if (newValue != null) {
                  attributes.put(attribute, getContextRelativeValue(newValue));
               }
            }
         }
      }
   }

   /**
    * Checks if <code>value</code> represents a path and if it does transforms it into a relative
    * path
    * 
    * @param value
    * @return value as a relative path or untouched if not a path
    */
   protected String getContextRelativeValue(String value) {
      if ("href".equals(attribute) || "src".equals(attribute)) {
         RequestContext rc = RequestContext.get();
         if (rc.isPortletRequest() &&
            !(value.startsWith("http://") || value.startsWith("https://"))) {
            if ("href".equals(attribute)) {
               value = ((PortletRequestContext)rc).encodeRenderURL(value).toString();
            }
            else {
               value = ((PortletRequestContext)rc).encodeSharedResourceURL(value).toString();
            }
         }
      }
      return value;
   }

   /**
    * Sets whether this attribute modifier is enabled or not.
    * 
    * @param enabled
    *            Whether enabled or not
    */
   public final void setEnabled(final boolean enabled)
   {
      this.enabled = enabled;
   }

   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      // JJV - removed replacementModel as it was causing a recursive infinite loop
      return "[CAttributeModifier attribute=" + attribute + ", enabled=" + enabled + ", pattern=" +
         pattern + "]";
   }

   /**
    * gets replacement with null check.
    * 
    * @param component
    * @return replacement value
    */
   private Object getReplacementOrNull(final Component component) {
      IModel<?> model = replaceModel;
      if (model instanceof IComponentAssignedModel)
      {
         model = ((IComponentAssignedModel<?>)model).wrapOnAssignment(component);
      }
      return (model != null) ? model.getObject() : null;
   }

   /**
    * gets replacement as a string with null check.
    * 
    * @param replacementValue
    * @return replacement value as a string
    */
   private String toStringOrNull(final Object replacementValue) {
      return (replacementValue != null) ? replacementValue.toString() : null;
   }

   /**
    * Gets the replacement model. Allows subclasses access to replace model.
    * 
    * @return the replace model of this attribute modifier
    */
   protected final IModel<?> getReplaceModel() {
      return replaceModel;
   }

   /**
    * Gets the value that should replace the current attribute value. This gives users the ultimate
    * means to customize what will be used as the attribute value. For instance, you might decide
    * to append the replacement value to the current instead of just replacing it as is Wicket's
    * default.
    * 
    * @param currentValue
    *            The current attribute value. This value might be null!
    * @param replacementValue
    *            The replacement value. This value might be null!
    * @return The value that should replace the current attribute value
    */
   protected String newValue(final String currentValue, final String replacementValue) {
      return replacementValue;
   }
}
