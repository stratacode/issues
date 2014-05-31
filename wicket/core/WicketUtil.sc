import sc.bind.*;
import sc.type.*;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

class WicketUtil {

   static void enableAjaxFormComponent(Component comp, String submitEvent, String changeEvent, Duration delay) {
      if (!comp.outputMarkupId) // Turn this on automatically if we're using ajax events
         comp.outputMarkupId = submitEvent != null || changeEvent != null;
      if (submitEvent != null) {
         SCFormComponentBehavior formBehavior = new SCFormComponentBehavior(submitEvent);
         if (delay != null)
            formBehavior.throttleDelay = delay;
         comp.add(formBehavior);
      }
      if (changeEvent != null) {
        Form form = comp.findParent(Form.class);
        if (form == null)
           System.err.println("No parent form for component with changeEvent: " + comp);
        else {
           SCFormValidatingBehavior compBehavior = new SCFormValidatingBehavior(form, changeEvent);
           if (delay != null)
              compBehavior.throttleDelay = delay;
           comp.add(compBehavior);
         }
      }
   }

   static void sendEvent(int event, Component obj, IBeanMapper prop) {
      Bind.sendEvent(event, obj, prop);
      obj.modelChanged(); // Is this necessary?
      AjaxRequestTarget target = SCRequestCycle.getCurrentAjaxRequestTarget();
      if (target != null) {
         // The component which is sending this ajax event does not need to be updated presumably
         // since it was updated on the client.  For text fields at least, replacing the active component
         // causes the focus and selection to change.
         Component currentComponent = SCRequestCycle.getCurrentRequestComponent();
         if (currentComponent != obj && obj.outputMarkupId) // Get an error if we haven't set outputMarkupId yet...
            target.addComponent(obj);
      }
   }
}
