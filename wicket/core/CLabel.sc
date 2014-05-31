
class CLabel extends Label {
   String submitEvent; // Turn on ajax form component "submit" functionality (i.e. onchange, onkeyup)
   String changeEvent; // Turn on ajax "change" functionality/validation etc for this event (i.e. onchange, onkeyup)
   Duration throttleDelay;

   void init() {
      WicketUtil.enableAjaxFormComponent(this, submitEvent, changeEvent, throttleDelay);
      if (defaultModel == null) 
         defaultModel = new PropertyModel(this, "textValue");
   }

   private String textValue;
   String getTextValue() {
      return textValue;
   }
   @Bindable(manual=true)
   void setTextValue(String val) {
      textValue = val;
      WicketUtil.sendEvent(sc.bind.IListener.VALUE_CHANGED, this, textValueProp);
   }

   private static sc.type.IBeanMapper textValueProp = sc.type.TypeUtil.getPropertyMapping(CLabel.class, "textValue");
}
