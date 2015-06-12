tomcat.serve extends tomcat.lib, html.core {
   public void start() {
      LayeredSystem system = getLayeredSystem();
      LayerFileProcessor configFileProcessor = new LayerFileProcessor();

      configFileProcessor.srcPathTypes = new String[] {"config"};
      configFileProcessor.prependLayerPackage = false;
      configFileProcessor.useSrcDir = false;

      // Copy these extensions to the output file
      registerFileProcessor(configFileProcessor, "xml");
      registerFileProcessor(configFileProcessor, "properties");
      registerFileProcessor(configFileProcessor, "policy");
      registerFileProcessor(configFileProcessor, "xsd");

      addSrcPath("conf", "config");
   }
}
