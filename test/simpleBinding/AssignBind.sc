
import sc.bind.BindSettings;

public class AssignBind {
   public int b1 = 3;
   public int b2 = 4;
   public int b3 = 5;
   public int b4 = 6;
   b1 =: b2 = 7;

   b4 =: b2 = b3 = 8;

   public class Type1 {
      public int b1 = 3;
      public int b2 :=: b1;
   }

   Type1 t1 = new Type1();

   b4 =: t1.b1 = 33;

   Type1 t2 = null;
   public int b5 = 7;
   b5 =: t2.b1 = 333;

   @MainSettings(test=true)
   public static void main(String[]args) {
      AssignBind bd = new AssignBind();

      System.out.println("4=" + bd.b2);
      bd.b1 = 2;
      System.out.println("7=" + bd.b2);
      System.out.println("5=" + bd.b3);
      System.out.println("3=" + bd.t1.b1);
      bd.b4 = 66;
      System.out.println("8=" + bd.b3);
      System.out.println("8=" + bd.b2);
      System.out.println("33=" + bd.t1.b1);

      bd.t2 = bd.new Type1();
      bd.b5 = 77;
      System.out.println("333=" + bd.t2.b1); // If it is 3, the binding did not fire.

      //System.out.println("All active bindings");
      //sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.removeBindings(bd);
      sc.bind.Bind.removeBindings(AssignBind.class);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(bd);
   }
}
