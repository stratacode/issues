
public class HSBind {

   public static class Type1 {
      public int b1 = 3;
      public int b2 :=: b1;
   }

   public static Type1 obj1 = new Type1();

   public static object obj2 extends Type1 {
      b1 = 30;
   }

   public static int b3 :=: obj1.b1;

   @MainSettings(test=true)
   public static void main(String[]args) {
      // Bi directional
      System.out.println("b1=" + HSBind.obj1.b1 + " b2=" + HSBind.obj1.b2 + " b3=" + HSBind.b3);
      assert HSBind.obj1.b1 == HSBind.obj1.b2 && HSBind.obj1.b1 == 3 && HSBind.b3 == 3;
      HSBind.obj1.b1 = 4; 
      System.out.println("All should be 4: b1=" + HSBind.obj1.b1 + " b2=" + HSBind.obj1.b2 + " b3=" + HSBind.b3);
      assert HSBind.obj1.b1 == HSBind.obj1.b2 && HSBind.obj1.b1 == 4 && HSBind.b3 == 4;
      HSBind.obj1.b2 = 5; 
      System.out.println("All should be 5: b1=" + HSBind.obj1.b1 + " b2=" + HSBind.obj1.b2 + " b3=" + HSBind.b3);
      assert HSBind.obj1.b1 == HSBind.obj1.b2 && HSBind.obj1.b1 == 5 && HSBind.b3 == 5;

      HSBind.obj1 = HSBind.obj2;
      System.out.println("All should be 30 b1=" + HSBind.obj1.b1 + " b2=" + HSBind.obj1.b2 + " b3=" + HSBind.b3);
      assert HSBind.obj1.b1 == HSBind.obj1.b2 && HSBind.obj1.b1 == 30 && HSBind.b3 == 30;
      HSBind.b3 = 40;
      System.out.println("All should be 40 b1=" + HSBind.obj1.b1 + " b2=" + HSBind.obj1.b2 + " b3=" + HSBind.b3);
      assert HSBind.obj1.b1 == HSBind.obj1.b2 && HSBind.obj1.b1 == 40 && HSBind.b3 == 40;
      HSBind.obj1.b1 = 50;
      System.out.println("All should be 50 b1=" + HSBind.obj1.b1 + " b2=" + HSBind.obj1.b2 + " b3=" + HSBind.b3);
      assert HSBind.obj1.b1 == HSBind.obj1.b2 && HSBind.obj1.b1 == 50 && HSBind.b3 == 50;
      HSBind.obj1.b2 = 60;
      System.out.println("All should be 60 b1=" + HSBind.obj1.b1 + " b2=" + HSBind.obj1.b2 + " b3=" + HSBind.b3);
      assert HSBind.obj1.b1 == HSBind.obj1.b2 && HSBind.obj1.b1 == 60 && HSBind.b3 == 60;
   }
}
