package sc.tomcat;
tomcat.disableServer extends lib {
   {
      // Since we are disabling the server, also disable the default URL
      layeredSystem.options.openPageAtStartup = false;
   }
}
