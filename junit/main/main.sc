
junit.main extends lib {
   exportPackage = false;
   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();
      registerAnnotationProcessor("org.junit.Test", new sc.junit.DebugAnnotationProcessor());
      system.buildInfo.registerTestProcessor("junit", new sc.junit.JUnitTestProcessor());
   }

   compiledOnly = true;
}
