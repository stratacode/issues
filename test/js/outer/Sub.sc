@MainInit
class Sub {
   int subField = 8;

   Sub() {
     System.out.println("0=" + Base.counter);
     System.out.println(Base.counter++ + "*** 8=" + subField);
     System.out.println("1=" + Base.counter);
     System.out.println("1=" + Base.counter++ + "*** 10=" + subInner.subInnerInner.subInnerInnerField);
     System.out.println("13=" + Base.counter);
     System.out.println(Base.counter++ + "*** 9=" + subInner.subInnerField);
     System.out.println("14=" + Base.counter);
   }

   object subInner extends Base { 
      int subInnerField = 9;

      object subInnerInner extends BaseInner {
         int subInnerInnerField = 10;
         subInnerInner() {
            System.out.println("In subInnerInner: counter=" + counter);
            System.out.println(counter++ + "*** 3=" + baseField);
            System.out.println(counter++ + "*** 8=" + subField);
            System.out.println(counter++ + "*** 9=" + subInnerField);
            System.out.println(counter++ + "*** 10=" + subInnerInnerField);
            innerTest();
         }
      }
 
      subInner() {
         System.out.println(counter++ + "*** 3=" + baseField);
         System.out.println(counter++ + "*** 8=" + subField);
         System.out.println(counter++ + "*** 9=" + subInnerField);
      }
   }
}
