import org.apache.wicket.Component;

class CRadioGroup<T> extends RadioGroup<T> {
   String submitEvent; // Turn on ajax "submit" functionality (i.e. onchange, onkeyup)
   String changeEvent; // Turn on ajax "validate" functionality for this event (i.e. onchange, onkeyup)
   Duration throttleDelay;

   boolean selectionChangedNotifications = false; /** Set this to true to enable a round-trip when selection changes */

   void init() {
      WicketUtil.enableAjaxFormComponent(this, submitEvent, changeEvent, throttleDelay);
      if (defaultModel == null)
         defaultModel = new PropertyModel(this, "currentValue");
   }

   // Set to true when the index is set before the component is defined.  This causes us to try and re-find the selection in onBeforeRender.
   // If the selection is changed before that time through the value, it is cleared
   private boolean choiceNotFound = false;

   private T mCurrentValue;
   T getCurrentValue() {
      return mCurrentValue;
   }

   @Bindable(manual=true)
   void setCurrentValue(T val) {
      mCurrentValue = val;
      if (mCurrentValue == null)
	 mCurrentChoiceIndex = -1;
      visitChildren(CRadio.class, new Component.IVisitor<CRadio<T>>() {
            int i = 0;
            Object component(CRadio<T> component) {
               if (mCurrentValue == component.getModelObject()) {
                  component.setSelectedInternal(true);
                  mCurrentChoiceIndex = i;
                  choiceNotFound = false;
               } 
               else {
                  component.setSelectedInternal(false);
               }
               i++;
               return Component.IVisitor.CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
            }
      });
      WicketUtil.sendEvent(sc.bind.IListener.VALUE_CHANGED, this, currentValueProp);
      WicketUtil.sendEvent(sc.bind.IListener.VALUE_CHANGED, this, currentChoiceIndexProp);
   }

   private int mCurrentChoiceIndex = -1;
   int getCurrentChoiceIndex() {
      return mCurrentChoiceIndex;
   }

   @Bindable(manual=true)
   void setCurrentChoiceIndex(int index) {
      mCurrentChoiceIndex = index;
      if (mCurrentChoiceIndex == -1)
	 mCurrentValue = null;
      if (updateChoiceFromIndex() == null)
         choiceNotFound = true;
      WicketUtil.sendEvent(sc.bind.IListener.VALUE_CHANGED, this, currentChoiceIndexProp);
   }

   void clearSelection() {
      setCurrentChoiceIndex(-1);
   }

   protected boolean wantOnSelectionChangedNotifications() {
      return selectionChangedNotifications;
   }

   private Boolean updateChoiceFromIndex() {
      return (Boolean) visitChildren(CRadio.class, new Component.IVisitor<CRadio<T>>() {
            int i = 0;
            Object component(CRadio<T> component) {
               if (i == currentChoiceIndex) {
                  component.setSelectedInternal(true);
                  mCurrentValue = component.getModelObject();
                  WicketUtil.sendEvent(sc.bind.IListener.VALUE_CHANGED, CRadioGroup.this, currentValueProp);
                  choiceNotFound = false;
                  return Boolean.TRUE;
               } else {
                  component.setSelectedInternal(false);
               }
               i++;
               return Component.IVisitor.CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
            }
         });
   }

   protected void onBeforeRender() {
      super.onBeforeRender();
      if (choiceNotFound)
         updateChoiceFromIndex();
   }

   private static sc.type.IBeanMapper currentValueProp = sc.type.TypeUtil.getPropertyMapping(CRadioGroup.class, "currentValue");
   private static sc.type.IBeanMapper currentChoiceIndexProp = sc.type.TypeUtil.getPropertyMapping(CRadioGroup.class, "currentChoiceIndex");
}
