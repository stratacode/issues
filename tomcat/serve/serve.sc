tomcat.serve extends tomcat.lib, html.core {
   public void start() {
      // Use the config file processors for the tomcat conf directory 
      addSrcPath("conf", "config");
   }
}
