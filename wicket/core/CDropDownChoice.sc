import java.util.List;
import org.apache.wicket.model.util.WildcardListModel;

class CDropDownChoice<T> extends DropDownChoice<T> {
   String submitEvent; // Turn on ajax "submit" functionality (i.e. onchange, onkeyup)
   String changeEvent; // Turn on ajax "validate" functionality for this event (i.e. onchange, onkeyup)
   Duration throttleDelay;

   boolean selectionChangedNotifications = false; /** Set this to true to enable a round-trip when selection changes */

   void init() {
      WicketUtil.enableAjaxFormComponent(this, submitEvent, changeEvent, throttleDelay);
      if (defaultModel == null)
         defaultModel = new PropertyModel(this, "currentValue");
   }

   int currentChoiceIndex;

   private T currentValue;
   T getCurrentValue() {
      return currentValue;
   }
   @Bindable(manual=true)
   void setCurrentValue(T val) {
      currentValue = val;
      WicketUtil.sendEvent(sc.bind.IListener.VALUE_CHANGED, this, currentValueProp);
      if (choiceValues == null)
         currentChoiceIndex = -1;
      else {
         for (int i = 0; i < choiceValues.size(); i++) {
            if (currentValue == choiceValues.get(i)) {
               currentChoiceIndex = i;
               break;
            }
         }
      }
   }

   private List<? extends T> choiceValues;
   List<? extends T> getChoiceValues() {
      return choiceValues;
   }
   void setChoiceValues(List<? extends T> cs) {
      choiceValues = cs;
      // Wrap in a model and propagate through to the underlying "choices" model wrapper
      setChoices(new WildcardListModel<T>(cs));
      WicketUtil.sendEvent(sc.bind.IListener.VALUE_CHANGED, this, choiceValuesProp);
   }

   private static sc.type.IBeanMapper currentValueProp = sc.type.TypeUtil.getPropertyMapping(CDropDownChoice.class, "currentValue");
   private static sc.type.IBeanMapper choiceValuesProp = sc.type.TypeUtil.getPropertyMapping(CDropDownChoice.class, "choiceValues");

   protected boolean wantOnSelectionChangedNotifications() {
      return selectionChangedNotifications;
   }
}
