broadleaf.core {
   object broadLeafPkg extends MvnRepositoryPackage {
      packageName = "broadleaf";
      type = "git-mvn";
      url = "git@github.com:BroadleafCommerce/BroadleafCommerce.git";

      srcPaths = {"src/main/java", "src/main/resources"};
   }

   {
      // Broadleaf currently only compiles with javac -source 1.7
      layeredSystem.javaSrcVersion = "1.7";
   }
}
