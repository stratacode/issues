package sc.jetty;

jetty.lib {
   compiledOnly = true;

   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.Program;

   public void initialize() {
      // Exclude the javascript runtime.  All layers which extend this layer explicitly will also be excluded, unless they explicitly include a layer which uses JS
      excludeRuntimes("js", "android", "gwt");

      // The servlet stuff requires the default runtime
      addRuntime(null);
   }

   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();

      // The web root is the original compiled buildDir, this tells sc to cd there before running any commands
      system.options.runFromBuildDir = true;
      // WEB-INF is not path searchable so the real build dir is the common build dir (doesn't change for dyn layers)
      system.options.useCommonBuildDir = true;

      // Layers web files in the "doc" folder of any downstream layers
      sc.layer.LayerFileProcessor log4jprops = new sc.layer.LayerFileProcessor();

      // Only layers after this one will see this extension
      log4jprops.definedInLayer = this;    
      log4jprops.prependLayerPackage = false;
      log4jprops.useSrcDir = false;

      // Copy this file into the top-level of the buildDir
      system.registerPatternFileProcessor("log4j\\.properties", log4jprops);

      //sc.repos.RepositoryPackage pkg = addRepositoryPackage("jettyLibs", "scp", "vsgit@stratacode.com:/home/vsgit/jettyLibs", false);
      sc.repos.RepositoryPackage pkg = addRepositoryPackage("jettyLibs", "url", "http://stratacode.com/packages/jettyLibs.zip", true);
      if (pkg.installedRoot != null) {
         classPath=sc.util.FileUtil.listFiles(pkg.installedRoot,".*\\.jar");
      }
   }
}
