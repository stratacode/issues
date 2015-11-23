broadleaf.core {
   object broadLeafPkg extends MvnRepositoryPackage {
      packageName = "broadleaf";
      type = "git-mvn";
      url = "git@github.com:BroadleafCommerce/BroadleafCommerce.git";

      srcPaths = {"src/main/java", "src/main/resources"};
   }
}
