tomcat.servlet extends tomcat.serve, servlet.schtml {
   defaultWebRoot = "webapps/ROOT";

   public void start() {
      // Put web files in the webapps/ROOT directory
      // the default is set in servle.webApp but we can override it here.
      setPathTypeBuildPrefix("web", defaultWebRoot);
   }
}
