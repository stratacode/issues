test.petclinic extends spring.example.petclinic, junit.main {
   // Set includeTests in initialize so that it's set when we install the package - starting the downstream layer
   public void init() {
      RepositoryPackage pkg = getRepositoryPackage("spring-petclinic");
      pkg.includeTests = true;
   }

   public void start() {
      RepositoryPackage pkg = getRepositoryPackage("spring-petclinic");
      srcPath = FileUtil.concat(pkg.installedRoot, "src", "test", "java");
   }
}
