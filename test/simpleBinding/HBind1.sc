
public class HBind1 {

   public object obj1 {
      public int b1 = 3;
      public int b2 :=: b1;
   }

   public object obj2 {
      public int b3 = 3;
      public int b4 := b3;
   }

   public object obj3 {
      public int b5 = 3;
      public int b6 =: b5;
   }

   @MainSettings(test=true)
   public static void main(String[]args) {
      HBind1 bd = new HBind1();

      // Bi directional
      System.out.println("b1=" + bd.obj1.b1 + " b2=" + bd.obj1.b2);
      assert bd.obj1.b1 == bd.obj1.b2 && bd.obj1.b1 == 3;
      bd.obj1.b1 = 4; 
      System.out.println("Both should be 4: b1=" + bd.obj1.b1 + " b2=" + bd.obj1.b2);
      assert bd.obj1.b1 == bd.obj1.b2 && bd.obj1.b1 == 4;
      bd.obj1.b2 = 5; 
      System.out.println("Both should be 5: b1=" + bd.obj1.b1 + " b2=" + bd.obj1.b2);
      assert bd.obj1.b1 == bd.obj1.b2 && bd.obj1.b1 == 5;

      // b4 gets changes from b3
      System.out.println("b3=" + bd.obj2.b3 + " b4=" + bd.obj2.b4);
      assert bd.obj2.b3 == bd.obj2.b4 && bd.obj2.b3 == 3;
      bd.obj2.b3 = 4; 
      System.out.println("Both should be 4: b3=" + bd.obj2.b3 + " b4=" + bd.obj2.b4);
      assert bd.obj2.b3 == bd.obj2.b4 && bd.obj2.b3 == 4;
      bd.obj2.b4 = 5; 
      System.out.println("Should be 4: b3=" + bd.obj2.b3 + " Should be 5=" + bd.obj2.b4);
      assert bd.obj2.b3 != bd.obj2.b4 && bd.obj2.b3 == 4;

      // b6 gets changes from b5
      System.out.println("b5=" + bd.obj3.b5 + " b6=" + bd.obj3.b6);
      // Interpreter does not do init same as compiler...
      //assert bd.obj3.b5 == bd.obj3.b6 && bd.obj3.b5 == 3;
      bd.obj3.b6 = 4; 
      System.out.println("Both should be 4: b5=" + bd.obj3.b5 + " b6=" + bd.obj3.b6);
      assert bd.obj3.b5 == bd.obj3.b6 && bd.obj3.b5 == 4;
      bd.obj3.b6 = 5; 
      System.out.println("Both should be 5: b5=" + bd.obj3.b5 + " b6=" + bd.obj3.b6);
      assert bd.obj3.b5 == bd.obj3.b6 && bd.obj3.b6 == 5;

      System.out.println("first=" + bd.obj3.b5 + " third=" + bd.obj3.b6 + bd.obj1.b1 + "sixth" + " seventh");
      System.out.println(11 + bd.obj3.b5 * 22 + bd.obj3.b6 * bd.obj1.b1 + 33 * 44);
      System.out.println(11 * bd.obj3.b5 * 22 + bd.obj3.b6 + bd.obj1.b1 * 33 * 44);

      //System.out.println("All active bindings");
      //sc.bind.Bind.printBindings(bd.obj1);
      //sc.bind.Bind.printBindings(bd.obj2);
      //sc.bind.Bind.printBindings(bd.obj3);
      sc.bind.Bind.removeBindings(bd.obj1);
      sc.bind.Bind.removeBindings(bd.obj2);
      sc.bind.Bind.removeBindings(bd.obj3);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.printBindings(bd.obj1);
      sc.bind.Bind.printBindings(bd.obj2);
      sc.bind.Bind.printBindings(bd.obj3);
   }
}
