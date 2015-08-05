jool.lib {
   object joolPkg extends MvnRepositoryPackage {
      packageName = "org.jooq/jool";
      type = "git-mvn";
      url = "git@github.com:jOOQ/jOOL.git";
   }
/*
   public void init() {
      sc.repos.RepositoryPackage pkg = addRepositoryPackage("org.jooq/jool", "git-mvn", "git@github.com:jOOQ/jOOL.git",  false);
   }
*/
   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();
      sc.repos.RepositoryPackage pkg = getRepositoryPackage("org.jooq/jool");
      if (pkg.installedRoot != null && !disabled) {
         srcPath = sc.util.FileUtil.concat(pkg.installedRoot, "src", "main", "java");
         //classPath = pkg.classPath;
      }
   }
}
