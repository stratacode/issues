class OuterObj1 {
   static class innerClass {
      String innerVar1;
      String innerVar2;
      String innerVar3;
      String innerVar4;
      int innerVar5;
      int innerVar6;
      String innerMethod(int foo) {
         return "inner";
      }
   }

   static object otherInnerObj {
      int otherVar1;
      int otherVar2;
   }


   static String outerVar;

   static String outerMethod(int foo) {
      return "outer";
   }

   // No fields or methods means this is a dummy class.  Make sure that all references
   // in this method get converted as needed to move to the getX method.
   static object innerObj extends innerClass {
      innerVar1 = outerVar;
      innerVar2 = innerMethod(3);
      innerVar3 = outerMethod(4);
      innerVar5 = otherInnerObj.otherVar1;
      innerVar6 = otherInnerObj.otherVar2;
   }
}
