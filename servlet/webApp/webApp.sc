package sc.servlet;

import sc.servlet.PathServlet;
import sc.servlet.PathServletFilter;

/** Defines basic web-app support.  Used by all servlet based frameworks for generating web.xml */
servlet.webApp extends util, html.schtml {
   compiledOnly = true;

   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.Program;

   public void initialize() {
      // Exclude the javascript runtime.  All layers which extend this layer explicitly will also be excluded, unless they explicitly include a layer which uses JS
      excludeRuntimes("js", "gwt", "android");

      // The servlet stuff requires the default runtime
      addRuntime(null);
   }

   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();

      sc.lang.DefaultAnnotationProcessor servletProc = new sc.lang.DefaultAnnotationProcessor();
      servletProc.typeGroupName = "servlets";
      servletProc.validOnField = false;
      servletProc.requiredType = "javax.servlet.Servlet";
      system.registerAnnotationProcessor("sc.servlet.PathServlet", servletProc);

      sc.lang.DefaultAnnotationProcessor servletFiltersProc = new sc.lang.DefaultAnnotationProcessor();
      servletFiltersProc.typeGroupName = "servletFilters";
      servletFiltersProc.validOnField = false;
      servletFiltersProc.requiredType = "javax.servlet.Filter";
      system.registerAnnotationProcessor("sc.servlet.PathServletFilter", servletFiltersProc);

      // When either the list of servlets or servlet filters changes, we need to regenerate
      // web.xml
      system.addTypeGroupDependency("web/WEB-INF/web.scxml", "web.WEB-INF.web", "servlets");
      system.addTypeGroupDependency("web/WEB-INF/web.scxml", "web.WEB-INF.web", "servletFilters");
   }
}
