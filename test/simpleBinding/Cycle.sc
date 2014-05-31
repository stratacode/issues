/* This test should compile as is... to test cycle errors, uncompile the various chunks and each should get an error */
public class Cycle {

   //public int simpleCycle := simpleCycle;

   //public int twoLevelA := twoLevelB;
   //public int twoLevelB := twoLevelA;

   //public int threeLevelA = threeLevelB + 2;
   //public int threeLevelB = threeLevelC + 3;
   //public int threeLevelC = threeLevelA + 1; 

   // This is an example which passes our cycle detection but fails Java's...
   // no cycles but references to following fields.
   public int complexA := Math.max(complexB * 3 * complexC, complexD);
   public int complexB := Math.max(complexD, complexC);
   public int complexC := Math.max(complexD * 4, 16);
   public int complexD := Math.max(3, 16);

   // Complex cycle - should get an error
   /*
   public int complexA := Math.max(complexB * 3 * complexC, complexD);
   public int complexB := Math.max(complexD, complexC);
   public int complexC := Math.max(complexD * 4, 16);
   public int complexD := Math.max(3, complexA);
   */

   /* Should get an error if @Component is not here */
   @Component
   public object obj1 {
      public int b1 = 1;
      public int b2 := obj2.b3;
   }

   @Component
   public object obj2 {
      public int b3 = 3;
      public int b4 := obj1.b1;
   }

   @MainSettings(test=true)
   public static void main(String[]args) {
      Cycle bd = new Cycle();
      System.out.println("*** 1=" + bd.obj2.b4 + " 3=" + bd.obj1.b2 + " 3=" + bd.obj2.b3 + " 1=" + bd.obj1.b1);
      assert bd.obj1.b1 == bd.obj2.b4 && bd.obj1.b1 == 1 && bd.obj2.b3 == 3 && bd.obj1.b2 == bd.obj2.b3;
   }
}
