test.jool extends junit.main, jool.lib {
   public void init() {
      sc.repos.RepositoryPackage pkg = getRepositoryPackage("org.jooq/jool");
      pkg.includeTests = true;
   }

   public void start() {
      // TODO - move this to some base layer - sys/resourceFiles or something?   Spring, swing, also put various file types in the
      // classpath
      LayerFileProcessor resourceFileProcessor = new LayerFileProcessor();
      resourceFileProcessor.prependLayerPackage = false;
      resourceFileProcessor.useSrcDir = false;
      resourceFileProcessor.useClassesDir = true;
      registerFileProcessor(resourceFileProcessor, "xml");
      registerFileProcessor(resourceFileProcessor, "sql");
      registerFileProcessor(resourceFileProcessor, "properties");

      RepositoryPackage pkg = getRepositoryPackage("org.jooq/jool");
      if (pkg.installedRoot != null) {
         srcPath = FileUtil.concat(pkg.installedRoot, "src", "test", "java");
         addSrcPath(FileUtil.concat(pkg.installedRoot, "src", "test", "resources"), null);
      }
   }
}
