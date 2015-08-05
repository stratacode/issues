// These are now picked up by setting includeRuntime = true
//spring.example.petclinic extends tomcat.lib, tomcat.jdbc, aspectj.rt, aspectj.weaver, slf4j, jdbc.hsql {
spring.example.petclinic extends tomcat.servlet, spring.std {
   inheritPackage = false;

   object petClinicPkg extends MvnRepositoryPackage {
      packageName = "spring-petclinic";
      // git-mvn uses git to check out the src, and maven to get the dependencies
      type = "git-mvn";
      url = "git@github.com:spring-projects/spring-petclinic.git";
      includeRuntime = true;
   }

   public void start() {
      // TODO: configure these as properties of the RepositoryPackage - 
      // srcPaths = {"java", "resources"}
      // webPath = "webApp";
      if (petClinicPkg.installedRoot != null && !disabled) {
         String mainDir = FileUtil.concat(petClinicPkg.installedRoot, "src", "main");
         addSrcPath(FileUtil.concat(mainDir, "java"), null);
         addSrcPath(FileUtil.concat(mainDir, "resources"), null);
         addSrcPath(FileUtil.concat(mainDir, "webapp"), "web");
      }
   }
}
