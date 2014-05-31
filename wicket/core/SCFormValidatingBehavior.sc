import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;

class SCFormValidatingBehavior extends AjaxFormValidatingBehavior {
   public SCFormValidatingBehavior(Form<?> form, String event) {
      super(form, event);
   }

   protected void onEvent(final AjaxRequestTarget target) {
      SCRequestCycle.setCurrentAjaxRequestTarget(target);
      SCRequestCycle.setCurrentRequestComponent(getComponent());
      super.onEvent(target);
      WicketApplication.flushEvents();
      SCRequestCycle.setCurrentAjaxRequestTarget(null);
      SCRequestCycle.setCurrentRequestComponent(null);
   }

   /*
   protected void onError(AjaxRequestTarget target) {
      SCRequestCycle.setCurrentAjaxRequestTarget(target);
      SCRequestCycle.setCurrentRequestComponent(getComponent());
      super.onError(target);
      WicketApplication.flushEvents();
      SCRequestCycle.setCurrentAjaxRequestTarget(null);
      SCRequestCycle.setCurrentRequestComponent(null);
   }
   */
}
