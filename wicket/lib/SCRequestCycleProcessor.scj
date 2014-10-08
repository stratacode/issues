import org.apache.wicket.protocol.http.WebRequestCycleProcessor;
import org.apache.wicket.request.RequestParameters;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.request.target.component.IPageRequestTarget;
import sc.dyn.DynUtil;
import sc.type.TypeUtil;
import org.apache.wicket.request.target.component.IBookmarkablePageRequestTarget;

class SCRequestCycleProcessor extends WebRequestCycleProcessor {
  public IRequestTarget resolve(final RequestCycle requestCycle, final RequestParameters requestParameters) {
     IRequestTarget target = super.resolve(requestCycle, requestParameters);
     Class pageClass = null;  
     if (target instanceof IBookmarkablePageRequestTarget) {
        pageClass = ((IBookmarkablePageRequestTarget) target).getPageClass();
     }
     if (target instanceof IPageRequestTarget) {
        IPageRequestTarget pt = (IPageRequestTarget) target;
        pageClass = pt.getPage().getClass();
     }
     if (pageClass != null) {
        DynUtil.refreshType(TypeUtil.getTypeName(pageClass, false));
     }
     return target;
  }
}
