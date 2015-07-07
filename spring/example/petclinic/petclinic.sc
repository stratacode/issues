// These are now picked up by setting includeRuntime = true
//spring.example.petclinic extends tomcat.lib, tomcat.jdbc, aspectj.rt, aspectj.weaver, slf4j, jdbc.hsql {
spring.example.petclinic extends tomcat.servlet {
   inheritPackage = false;
   public void init() {
      // git-mvn uses git to check out the src, and maven to get the dependencies
      // Adding it in the initialize method so that downstream layers can modify the package - e.g. to turn on loading of 'tests'
      RepositoryPackage pkg = addRepositoryPackage("spring-petclinic", "git-mvn", "git@github.com:spring-projects/spring-petclinic.git", false);
      pkg.includeRuntime = true;
      RepositoryPackage validatorPkg = addRepositoryPackage("mvn://javax.validation/validation-api/1.1.0.Final");
   }
   public void start() {
      // TODO - move this to some base layer - spring, or something?
      LayerFileProcessor resourceFileProcessor = new LayerFileProcessor();
      resourceFileProcessor.prependLayerPackage = false;
      resourceFileProcessor.useSrcDir = false;
      resourceFileProcessor.useClassesDir = true;
      registerFileProcessor(resourceFileProcessor, "xml");
      registerFileProcessor(resourceFileProcessor, "sql");
      registerFileProcessor(resourceFileProcessor, "properties");

      RepositoryPackage validatorPkg = getRepositoryPackage("javax.validation/validation-api");

      // TODO: move to a MvnRepository method - Mvn.addPackage(this, name)
      // git-mvn uses git to check out the src, and maven to get the dependencies
      RepositoryPackage pkg = getRepositoryPackage("spring-petclinic");
      if (pkg.installedRoot != null && !disabled) {
         String mainDir = FileUtil.concat(pkg.installedRoot, "src", "main");
         addSrcPath(FileUtil.concat(mainDir, "java"), null);
         addSrcPath(FileUtil.concat(mainDir, "resources"), null);
         addSrcPath(FileUtil.concat(mainDir, "webapp"), "web");
         classPath = validatorPkg.classPath + ":" + pkg.classPath;
      }
   }
}
