class CRadio<T> extends Radio<T> {
   String submitEvent; // Turn on ajax "submit" functionality (i.e. onchange, onkeyup)
   String changeEvent; // Turn on ajax "validate" functionality for this event (i.e. onchange, onkeyup)
   Duration throttleDelay;

   void init() {
      WicketUtil.enableAjaxFormComponent(this, submitEvent, changeEvent, throttleDelay);
      if (defaultModel == null)
         defaultModel = new PropertyModel(this, "choiceValue");
   }

   private T choiceValue;
   T getChoiceValue() {
      return choiceValue;
   }
   @Bindable(manual=true)
   void setChoiceValue(T val) {
      choiceValue = val;
      WicketUtil.sendEvent(sc.bind.IListener.VALUE_CHANGED, this, choiceValueProp);
   }

   // Read-only property
   private boolean selected;
   boolean isSelected() {
      return selected;
   }
   @Bindable(manual=true)
   protected void setSelectedInternal(boolean sel) {
      if (sel != selected) {
	 selected = sel;
	 WicketUtil.sendEvent(sc.bind.IListener.VALUE_CHANGED, this, selectedProp);
      }
   }

   private static sc.type.IBeanMapper choiceValueProp = sc.type.TypeUtil.getPropertyMapping(CRadio.class, "choiceValue");
   private static sc.type.IBeanMapper selectedProp = sc.type.TypeUtil.getPropertyMapping(CRadio.class, "selected");
}
