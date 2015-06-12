package sc.tomcat;

tomcat.jdbc {
   compiledOnly = true;

   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.Program;

   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();

      RepositoryPackage pkg = addRepositoryPackage("mvn://org.apache.tomcat/tomcat-jdbc/8.0.23");
      if (pkg.installedRoot != null && !disabled) {
         classPath = pkg.classPath;
      }
   }
}
