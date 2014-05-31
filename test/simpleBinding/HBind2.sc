
public class HBind2 {

   public class Type1 {
      public int b1 = 3;
      public int b2 :=: b1;
   }

   public Type1 obj1 = new Type1();

   public object obj2 extends Type1 {
      b1 = 30;
   }

   public int b3 :=: obj1.b1;

   @MainSettings(test=true)
   public static void main(String[]args) {
      HBind2 bd = new HBind2();

      // Bi directional
      System.out.println("b1=" + bd.obj1.b1 + " b2=" + bd.obj1.b2 + " b3=" + bd.b3);
      assert bd.obj1.b1 == bd.obj1.b2 && bd.obj1.b1 == 3 && bd.b3 == 3;
      bd.obj1.b1 = 4; 
      System.out.println("All should be 4: b1=" + bd.obj1.b1 + " b2=" + bd.obj1.b2 + " b3=" + bd.b3);
      assert bd.obj1.b1 == bd.obj1.b2 && bd.obj1.b1 == 4 && bd.b3 == 4;
      bd.obj1.b2 = 5; 
      System.out.println("All should be 5: b1=" + bd.obj1.b1 + " b2=" + bd.obj1.b2 + " b3=" + bd.b3);
      assert bd.obj1.b1 == bd.obj1.b2 && bd.obj1.b1 == 5 && bd.b3 == 5;

      bd.obj1 = bd.obj2;
      System.out.println("All should be 30 b1=" + bd.obj1.b1 + " b2=" + bd.obj1.b2 + " b3=" + bd.b3);
      assert bd.obj1.b1 == bd.obj1.b2 && bd.obj1.b1 == 30 && bd.b3 == 30;
      bd.b3 = 40;
      System.out.println("All should be 40 b1=" + bd.obj1.b1 + " b2=" + bd.obj1.b2 + " b3=" + bd.b3);
      assert bd.obj1.b1 == bd.obj1.b2 && bd.obj1.b1 == 40 && bd.b3 == 40;
      bd.obj1.b1 = 50;
      System.out.println("All should be 50 b1=" + bd.obj1.b1 + " b2=" + bd.obj1.b2 + " b3=" + bd.b3);
      assert bd.obj1.b1 == bd.obj1.b2 && bd.obj1.b1 == 50 && bd.b3 == 50;
      bd.obj1.b2 = 60;
      System.out.println("All should be 60 b1=" + bd.obj1.b1 + " b2=" + bd.obj1.b2 + " b3=" + bd.b3);
      assert bd.obj1.b1 == bd.obj1.b2 && bd.obj1.b1 == 60 && bd.b3 == 60;

      //System.out.println("All active bindings");
      //sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.removeBindings(bd);
      sc.bind.Bind.removeBindings(HBind2.class);
      sc.bind.Bind.removeBindings(bd.obj1);
      sc.bind.Bind.removeBindings(bd.obj2);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.printBindings(bd.obj1);
      sc.bind.Bind.printBindings(bd.obj2);
   }
}
