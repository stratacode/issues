jdbc.mysql {
   public void start() {
      String componentName = "mysql-connector-java-";
      String version = "5.1.35";
      sc.layer.LayeredSystem system = getLayeredSystem();

      //sc.repos.RepositoryPackage pkg = addRepositoryPackage("jettyLibs", "scp", "vsgit@stratacode.com:/home/vsgit/jettyLibs", false);
      sc.repos.RepositoryPackage pkg = addRepositoryPackage("mysqlJDBC", "url", "http://stratacode.com/packages/" + componentName + version + ".zip", true);

      if (pkg.installedRoot != null && !disabled) {
         String rootName = sc.util.FileUtil.concat(pkg.installedRoot, componentName + version);
         classPath = sc.util.FileUtil.listFiles(rootName,".*\\.jar");
      }
   }
}
