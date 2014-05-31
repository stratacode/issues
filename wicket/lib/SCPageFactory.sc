import sc.type.RTypeUtil;
import sc.dyn.DynUtil;
import sc.obj.IComponent;

import org.apache.wicket.IPageFactory;
import org.apache.wicket.PageParameters;
import org.apache.wicket.session.DefaultPageFactory;

class SCPageFactory implements IPageFactory {
   DefaultPageFactory def = new DefaultPageFactory();
   <C extends Page> Page newPage(final Class<C> pageClass) {
      // Done in the resolve method of RequestProcessor
      //DynUtil.refreshType(TypeUtil.getTypeName(pageClass, false));
      if (IComponent.class.isAssignableFrom(pageClass))
         return (Page) RTypeUtil.newComponent(pageClass);
      else
         return def.newPage(pageClass);
   }

   <C extends Page> Page newPage(final Class<C> pageClass, PageParameters parameters) {
      // Done in the resolve method of RequestProcessor
      //DynUtil.refreshType(TypeUtil.getTypeName(pageClass, false));
      if (IComponent.class.isAssignableFrom(pageClass))
         return (Page) RTypeUtil.newComponent(pageClass, parameters.values().toArray());
      else
         return def.newPage(pageClass);
   }
}
