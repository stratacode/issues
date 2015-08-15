jool.lib {
   object joolPkg extends MvnRepositoryPackage {
      packageName = "org.jooq/jool";
      type = "git-mvn";
      url = "git@github.com:jOOQ/jOOL.git";

      srcPaths = {"src/main/java"};
   }
}
