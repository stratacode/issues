log4j {
   compiledOnly = true;

   object slf4jApiPkg extends MvnRepositoryPackage {
      url = "mvn://org.slf4j/slf4j-api/1.7.0";
   }

/*
   object log4jPkg extends MvnRepositoryPackage {
      url = "mvn://org.slf4j/slf4j-log4j12/1.7.0";
   }
*/

   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();

      // Layers web files in the "doc" folder of any downstream layers
      LayerFileProcessor log4jprops = new sc.layer.LayerFileProcessor();

      // Only layers after this one will see this extension
      log4jprops.definedInLayer = this;    
      log4jprops.prependLayerPackage = false;
      // Copy this to the same place compiled classes go
      log4jprops.useSrcDir = false;
      log4jprops.useClassesDir = true;

      // Copy this file into the top-level of the buildDir
      system.registerPatternFileProcessor("log4j\\.properties", log4jprops);
   }
}
