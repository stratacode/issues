log4j extends sys.std {
   compiledOnly = true;

   // Log4j complains if we include two incompatible implementation classes and so higher level 
   // packages need to define the impl library.  Here we only pull in the api.

   object slf4jApiPkg extends MvnRepositoryPackage {
      url = "mvn://org.slf4j/slf4j-api/1.7.0";
   }

   resourceFileProcessor {
      {
         // Treat this file as a resource so it goes in the classpath
         addPatterns("log4j\\.properties");
      }
   }

   public void init() {
      excludeRuntimes("js", "android", "gwt");
   }
}
