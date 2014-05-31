import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.persistence.IValuePersister;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.util.string.AppendingStringBuffer;

/**
 * Like AjaxFormComponentUpdatingBehavior but extended to handle binding events in the ajax context
 */
public class SCFormComponentBehavior extends AjaxEventBehavior {
   private static final long serialVersionUID = 1L;

   public SCFormComponentBehavior(final String event) {
      super(event);
   }

   protected void onBind() {
      super.onBind();

      if (!(getComponent() instanceof FormComponent)) {
         throw new WicketRuntimeException("Behavior " + getClass().getName() +
            " can only be added to a child of a FormComponent");
      }
   }

   protected final FormComponent<?> getFormComponent() {
      return (FormComponent<?>)getComponent();
   }

   protected final CharSequence getEventHandler() {
      return generateCallbackScript(new AppendingStringBuffer("wicketAjaxPost('").append(
         getCallbackUrl(false)).append(
         "', wicketSerialize(Wicket.$('" + getComponent().getMarkupId() + "'))"));
   }

   protected void onCheckEvent(String event) {
      if ("href".equalsIgnoreCase(event)) {
         throw new IllegalArgumentException(
            "this behavior cannot be attached to an 'href' event");
      }
   }

   protected final void onEvent(final AjaxRequestTarget target) {
      final FormComponent<?> formComponent = getFormComponent();

      if (getEvent().toLowerCase().equals("onblur") && disableFocusOnBlur())
      {
         target.focusComponent(null);
      }

      try
      {
         SCRequestCycle.setCurrentAjaxRequestTarget(target);
         SCRequestCycle.setCurrentRequestComponent(getComponent());
         formComponent.inputChanged();
         formComponent.validate();
         if (formComponent.hasErrorMessage())
         {
            formComponent.invalid();

            //onError(target, null);
         }
         else
         {
            formComponent.valid();
            if (getUpdateModel())
            {
               formComponent.updateModel();
            }

            //onUpdate(target);
         }
         WicketApplication.flushEvents();
      }
      catch (RuntimeException e)
      {
         //onError(target, e);

      }
      finally {
         SCRequestCycle.setCurrentAjaxRequestTarget(null);
         SCRequestCycle.setCurrentRequestComponent(null);
      }
   }

   protected boolean getUpdateModel() {
      return true;
   }

   protected boolean disableFocusOnBlur() {
      return true;
   }
}
