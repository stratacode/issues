
junit.main extends lib {
   exportPackage = false;
   public void start() {
      if (activated) {
         registerAnnotationProcessor("org.junit.Test", new sc.junit.DebugAnnotationProcessor());
         layeredSystem.buildInfo.registerTestProcessor("junit", new sc.junit.JUnitTestProcessor());
      }
   }

   compiledOnly = true;
}
