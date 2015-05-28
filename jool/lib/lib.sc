jool.lib {
   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();
      sc.repos.RepositoryPackage pkg = addRepositoryPackage("jool", "git", "git@github.com:jOOQ/jOOL.git",  false);
      if (pkg.installedRoot != null && !disabled) {
         srcPath=sc.util.FileUtil.concat(pkg.installedRoot, "src", "main", "java");
      }
   }
}
