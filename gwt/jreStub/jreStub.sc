gwt.jreStub {

   disabled = true;

   void init() {
      excludeRuntimes("js", "java", "android");
   }

   void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();
      sc.layer.LayerFileProcessor configFileProcessor = new sc.layer.LayerFileProcessor();

      // Only layers after this one will see this extension
      configFileProcessor.definedInLayer = this;    
      configFileProcessor.prependLayerPackage = true;

      // Copy these extensions to the output file.  Needed for gwt.xml files, using src because that's where the gwt compiler looks for it.
      configFileProcessor.useSrcDir = true;
      system.registerPatternFileProcessor(".*.gwt.xml", configFileProcessor);

      // Add this directory as the src path.  Note this constrains us to not using a package prefix for this layer but is simpler than trying to copy the sources elsewhere so the GWT compiler can find them.
      system.addSystemClassDir(layerPathName);
   }
}
