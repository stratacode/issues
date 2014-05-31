import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.markup.html.form.persistence.IValuePersister;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.Component;

class CForm<T> extends Form<T> {
   public boolean process() {
       if (!isEnabledInHierarchy() || !isVisibleInHierarchy()) {
          // since process() can be called outside of the default form workflow, an additional
          // check is needed
          return false;
       }

       // run validation
       validate();

       // If a validation error occurred
       if (hasError()) {
          // mark all children as invalid
          markFormComponentsInvalid();

          // let subclass handle error
          callOnError();

          // Form has an error
          return false;
       }
       else {
          // mark all children as valid
          markFormComponentsValid();

          // before updating, call the interception method for clients
          beforeUpdateFormComponentModels();

          WicketApplication.queueEvents();

          // Start queueing form events here 
          try {
             // Update model using form data
             updateFormComponentModels();
          }
          finally {
             WicketApplication.flushEvents();
          }

          // Persist FormComponents if requested
          persistFormComponentData();

          // Form has no error
          return true;
       }
   }

   private void onErrorWrap() {
     onError();
   }

   /**
    * Calls onError on this {@link Form} and any enabled and visible nested form, if the respective
    * {@link Form} actually has errors.
    */
   private void callOnError()
   {
           onError();
           // call onError on nested forms
           visitChildren(CForm.class, new IVisitor<Component>()
           {
                   public Object component(Component component)
                   {
                           final CForm<?> form = (CForm<?>)component;
                           if (!form.isEnabledInHierarchy() || !form.isVisibleInHierarchy())
                           {
                                   return IVisitor.CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
                           }
                           if (form.hasError())
                           {
                                   form.onErrorWrap();
                           }
                           return IVisitor.CONTINUE_TRAVERSAL;
                   }
           });
   }


   /**
    * Persist (e.g. Cookie) FormComponent data to be reloaded and re-assigned to the FormComponent
    * automatically when the page is visited by the user next time.
    * 
    * @see org.apache.wicket.markup.html.form.FormComponent#updateModel()
    */
   private void persistFormComponentData() {
      // Cannot add cookies to request cycle unless it accepts them
      // We could conceivably be HTML over some other protocol!
      if (getRequestCycle() instanceof WebRequestCycle) {
         // The persistence manager responsible to persist and retrieve
         // FormComponent data
         final IValuePersister persister = getValuePersister();

         // Search for FormComponent children. Ignore all other
         visitFormComponentsPostOrder(new FormComponent.AbstractVisitor() {
             @Override
             public void onFormComponent(final FormComponent<?> formComponent) {
                if (formComponent.isVisibleInHierarchy()) {
                   // If persistence is switched on for that FormComponent
                   // ...
                   if (formComponent.isPersistent()) {
                      // Save component's data (e.g. in a cookie)
                      persister.save(formComponent);
                   }
                   else {
                      // Remove component's data (e.g. cookie)
                      persister.clear(formComponent);
                   }
                }
             }
         });
      }
   }
}


