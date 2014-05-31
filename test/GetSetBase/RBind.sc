public class RBind {
   object testInst implements sc.bind.IChangeable {
       String testValue = new String("testValue");
       Object currentType :=: otherInst.currentType;

       void trigger() {
          Bind.sendChangedEvent(this, null);
       }
   }

   object otherInst {
      String otherValue = new String("otherValue");
      Object currentType = otherValue;
   }


   @Test
   public void doTest() {
      assertTrue(otherInst.currentType == otherInst.otherValue);
      assertTrue(testInst.currentType == otherInst.otherValue);

      testInst.currentType = testInst.testValue;

      assertTrue(otherInst.currentType == testInst.testValue);
      assertTrue(testInst.currentType == testInst.testValue);

      testInst.trigger();

      assertTrue(otherInst.currentType == testInst.testValue);
      assertTrue(testInst.currentType == testInst.testValue);
   }
}
