log4j {
   compiledOnly = true;

   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();

      // Layers web files in the "doc" folder of any downstream layers
      sc.layer.LayerFileProcessor log4jprops = new sc.layer.LayerFileProcessor();

      // Only layers after this one will see this extension
      log4jprops.definedInLayer = this;    
      log4jprops.prependLayerPackage = false;
      // Copy this to the same place compiled classes go
      log4jprops.useSrcDir = false;
      log4jprops.useClassesDir = true;

      // Copy this file into the top-level of the buildDir
      system.registerPatternFileProcessor("log4j\\.properties", log4jprops);

      // TODO add a repositoryPackage to install the log4j.jar file.  For now, it's bundled with jetty and datanucleus
   }
}
