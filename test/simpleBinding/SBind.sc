
public class SBind {
   class Inner1 {
      Object ptr;
      Inner2 inner2 = new Inner2();
      {
         ptr = inner2;
      }
   }

   Inner1 inner1 = new Inner1();

   class Inner2 {
      Object ptr;
      int v1 = 1;
   }

   int res1 := ((Inner2) inner1.ptr).v1;
   int res2 := inner1.inner2.v1;

   @MainSettings(test=true)
   public static void main(String[]args) {
      SBind bd = new SBind();

      System.out.println("1=" + bd.res1 + " 1=" + bd.res2);
      bd.inner1.inner2.v1 = 2;
      System.out.println("2=" + bd.res1 + " 2=" + bd.res2);

      sc.bind.Bind.removeBindings(bd);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(bd);
   }
}
