test.jool extends junit.main, jool.lib {
   public void start() {
      sc.repos.RepositoryPackage pkg = getRepositoryPackage("jool");
      srcPath = sc.util.FileUtil.concat(pkg.installedRoot, "src", "test", "java");
   }
}
