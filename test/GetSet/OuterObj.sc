class OuterObj {
   class innerClass {
      String innerVar1;
      String innerVar2;
      String innerVar3;
      String innerVar4;
      String innerMethod(int foo) {
         return "inner";
      }
   }


   String outerVar;

   String outerMethod(int foo) {
      return "outer";
   }

   object innerObj extends innerClass {
      innerVar1 = outerVar;
      innerVar2 = innerMethod(3);
      innerVar3 = outerMethod(4);
   }
}
