test.petclinic extends spring.example.petclinic, junit.main, tomcat.disableServer {
   // Set includeTests in initialize so that it's set when we install the package - starting the downstream layer

   // Including this because it needs to include the dependencies in 'runtime' scope
   object mockitoPkg extends MvnRepositoryPackage {
      url = "mvn://org.mockito/mockito-core/1.10.19";
      includeRuntime = true;
   }

   public void init() {
      RepositoryPackage pkg = getRepositoryPackage("spring-petclinic");
      pkg.includeTests = true;
   }

   public void start() {
      RepositoryPackage pkg = getRepositoryPackage("spring-petclinic");
      srcPath = FileUtil.concat(pkg.installedRoot, "src", "test", "java");
   }
}
