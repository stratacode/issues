import sc.layer.ITestProcessor;
import sc.dyn.RDynUtil;
import sc.dyn.DynUtil;

public class JUnitTestProcessor implements ITestProcessor {
   public boolean executeTest(Object cl) {
      if (cl instanceof Class)
         return JUnitCore.runClasses((Class) cl).wasSuccessful();
      else {
         boolean result = true;
         Object testInst = DynUtil.createInstance(cl, null);
         Object[] methods = RDynUtil.getAllMethods(cl, "public", true);

         if (runAllMethods(cl, methods, org.junit.Before.class, testInst) &&
             runAllMethods(cl, methods, org.junit.Test.class, testInst)) { 
            System.out.println("Test: " + cl + " passed");
         }
         else
            result = false;
         return result;
      }
   }

   private boolean runAllMethods(Object cl, Object[] methods, Class annotClass, Object testInst) {
      boolean result = true;
      for (int i = 0; i < methods.length; i++) {
         try {
            // TODO: should catch exceptions and check the Test.expected value to see if it mentions that
            // probably other junit stuff we should do
            if (RDynUtil.getAnnotation(methods[i], annotClass) != null) {
               DynUtil.invokeMethod(testInst, methods[i]);
            }
         }
         catch (AssertionError e) {
            System.err.println("*** Test failed: " + cl + "." + methods[i] + ": " + e);
            result = false;
         }
      }
      return result;
   }
}
