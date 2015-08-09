spring.example.petclinic extends tomcat.servlet, spring.std {
   // Do not inherit the package of tomcat.servlet.  Since this layer
   // uses sources in a normal Java package directory tree, it does not
   // restrict classes to any specific package.
   inheritPackage = false;

   object petClinicPkg extends MvnRepositoryPackage {
      packageName = "spring-petclinic";
      // The git-mvn repository uses git to check out the src, 
      // and maven to get the dependencies
      type = "git-mvn";
      url = "git@github.com:spring-projects/spring-petclinic.git";

      // Include maven dependencies marked <scope>runtime</scope>
      includeRuntime = true;

      srcPaths = {"src/main/java", "src/main/resources"};
      webPaths = {"src/main/webapp"};
   }
}
