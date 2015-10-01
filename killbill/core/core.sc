killbill.core {
   object killBillPkg extends MvnRepositoryPackage {
      packageName = "killbill";
      type = "git-mvn";
      url = "https://github.com/killbill/killbill";

      srcPaths = {"src/main/java", "src/main/resources"};
   }
}
