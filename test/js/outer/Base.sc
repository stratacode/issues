class Base {
   int baseField;

   static int counter = 0;

   Base() {
      System.out.println("In Base(): counter=" + counter);
      baseField = 3;
   }

   class BaseInner {
      int baseInnerField;

      BaseInner() {
         System.out.println("In BaseInner(): counter=" + counter);
         baseInnerField = 4;

         System.out.println(counter++ + "*** 3=" + baseField);
         System.out.println(counter++ + "*** 4=" + baseInnerField);
      }

      void innerTest() {
         System.out.println("In innerTest(): counter=" + counter);

         baseField = baseInnerField * 2;
         System.out.println(counter++ + "*** 8=" + baseField);
         baseInnerField = 7;
         System.out.println(counter++ + "*** 7=" + baseInnerField);
      }
   }
}
