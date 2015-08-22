package sc.tomcat;
tomcat.lib extends log4j {
   compiledOnly = true;

   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.Program;

   String tomcatVersion = "7.0.47";

   public void init() {
      // Exclude the javascript, android, and gwt runtimes.  All layers which extend this layer explicitly will also be excluded, unless they explicitly include a layer which uses JS
      excludeRuntimes("js", "android", "gwt");

      addProcess(sc.layer.ProcessDefinition.create("Server"));
   }

   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();

      // The web root is the original compiled buildDir, this tells sc to cd there before running any commands
      system.options.runFromBuildDir = true;
      // WEB-INF is not path searchable so the real build dir is the common build dir (doesn't change for dyn layers)
      system.options.useCommonBuildDir = true;

      //RepositoryPackage pkg = addRepositoryPackage("mvn://org.eclipse.jetty/jetty-webapp/8.1.17.v20150415");
      installPackages(new String[] {
         "mvn://org.apache.tomcat.embed/tomcat-embed-core/" + tomcatVersion, 
         "mvn://org.apache.tomcat/tomcat-catalina/" + tomcatVersion, 
         "mvn://org.apache.tomcat/tomcat-coyote/" + tomcatVersion, 
         "mvn://org.apache.tomcat/tomcat-util/" + tomcatVersion, 
         "mvn://org.apache.tomcat/tomcat-api/" + tomcatVersion, 
         "mvn://org.apache.tomcat/tomcat-jdbc/" + tomcatVersion, 
         "mvn://org.apache.tomcat/tomcat-dbcp/" + tomcatVersion, 
         "mvn://org.apache.tomcat/tomcat-servlet-api/" + tomcatVersion, 
         "mvn://org.apache.tomcat/tomcat-jsp-api/" + tomcatVersion, 
         "mvn://org.apache.tomcat/tomcat-jasper/" + tomcatVersion});
   }
}
