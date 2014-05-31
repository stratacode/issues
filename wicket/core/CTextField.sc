
class CTextField<T> extends TextField<T> {
   String submitEvent; // Turn on ajax "submit" functionality (i.e. onchange, onkeyup)
   String changeEvent; // Turn on ajax "validate" functionality for this event (i.e. onchange, onkeyup)
   Duration throttleDelay;

   void init() {
      WicketUtil.enableAjaxFormComponent(this, submitEvent, changeEvent, throttleDelay);
      if (defaultModel == null)
         defaultModel = new PropertyModel(this, "fieldValue");
   }

   private T fieldValue;
   T getFieldValue() {
      return fieldValue;
   }
   @Bindable(manual=true)
   void setFieldValue(T val) {
      fieldValue = val;
      WicketUtil.sendEvent(sc.bind.IListener.VALUE_CHANGED, this, fieldValueProp);
   }

   private static sc.type.IBeanMapper fieldValueProp = sc.type.TypeUtil.getPropertyMapping(CTextField.class, "fieldValue");
}
