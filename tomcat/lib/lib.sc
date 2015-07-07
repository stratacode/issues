package sc.tomcat;

tomcat.lib extends log4j {
   compiledOnly = true;

   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.Program;

   public void init() {
      // Exclude the javascript, android, and gwt runtimes.  All layers which extend this layer explicitly will also be excluded, unless they explicitly include a layer which uses JS
      excludeRuntimes("js", "android", "gwt");

      // Jetty requires the default runtime - Server process
      //addRuntime(null);
      addProcess(sc.layer.ProcessDefinition.create("Server"));
   }

   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();

      // The web root is the original compiled buildDir, this tells sc to cd there before running any commands
      system.options.runFromBuildDir = true;
      // WEB-INF is not path searchable so the real build dir is the common build dir (doesn't change for dyn layers)
      system.options.useCommonBuildDir = true;

      //sc.repos.RepositoryPackage pkg = addRepositoryPackage("jettyLibs", "scp", "vsgit@stratacode.com:/home/vsgit/jettyLibs", false);
      //sc.repos.RepositoryPackage pkg = addRepositoryPackage("jettyLibs", "url", "http://stratacode.com/packages/jettyLibs.zip", true);
      //RepositoryPackage pkg = addRepositoryPackage("mvn://org.eclipse.jetty/jetty-webapp/8.1.17.v20150415");
      installPackages(new String[] {
         "mvn://org.apache.tomcat/tomcat-catalina/7.0.47", 
         "mvn://org.apache.tomcat/tomcat-jasper/7.0.47"
         /* "mvn://javax.el/javax.el-api/3.0.0"*/});
   }
}
