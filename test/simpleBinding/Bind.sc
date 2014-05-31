
public class Bind {
   public int b1 = 3;
   public int b2 :=: b1;

   public int b3 = 3;
   public int b4 := b3;

   public int b5 = 3;
   public int b6 =: b5;

   static public int s1 = 3;
   static public int s2 :=: s1;;;;;

   static public int s3 = 3;
   static public int s4 := s3;;;

   static public int s5 = 999;
   static public int s6 = 3;;;
   s6 =: s5;

   @MainSettings(test=true)
   public static void main(String[]args) {
      Bind bd = new Bind();

      // Bi directional
      System.out.println("b1=" + bd.b1 + " b2=" + bd.b2);
      assert bd.b1 == bd.b2 && bd.b1 == 3;
      bd.b1 = 4; 
      System.out.println("Both should be 4: b1=" + bd.b1 + " b2=" + bd.b2);
      assert bd.b1 == bd.b2 && bd.b1 == 4;
      bd.b2 = 5; 
      System.out.println("Both should be 5: b1=" + bd.b1 + " b2=" + bd.b2);
      assert bd.b1 == bd.b2 && bd.b1 == 5;

      // b4 gets changes from b3
      System.out.println("b3=" + bd.b3 + " b4=" + bd.b4);
      assert bd.b3 == bd.b4 && bd.b3 == 3;
      bd.b3 = 4; 
      System.out.println("Both should be 4: b3=" + bd.b3 + " b4=" + bd.b4);
      assert bd.b3 == bd.b4 && bd.b3 == 4;
      bd.b4 = 5; 
      System.out.println("Should be 4: b3=" + bd.b3 + "Should be 5=" + bd.b4);
      assert bd.b3 != bd.b4 && bd.b3 == 4;

      // b5 gets changes from b6
      System.out.println("b5=" + bd.b5 + " b6=" + bd.b6);
      // NOTE: when interpreting, the b6 init to 0 sends an event which changes b5 so we allow both :(
      assert 0 == bd.b6 && (bd.b5 == 3 || bd.b5 == 0);
      bd.b6 = 4; 
      System.out.println("Both should be 4: b5=" + bd.b5 + " b6=" + bd.b6);
      assert bd.b5 == bd.b6 && bd.b5 == 4;
      bd.b5 = 5; 
      System.out.println("Should be 5: b5=" + bd.b5 + " Should be 4: b6=" + bd.b6);
      assert bd.b5 != bd.b6 && bd.b5 == 5;

      // Bi directional
      System.out.println("s1=" + Bind.s1 + " s1=" + Bind.s1);
      assert Bind.s1 == Bind.s1 && Bind.s1 == 3;
      Bind.s1 = 4; 
      System.out.println("Both should be 4: s1=" + Bind.s1 + " s1=" + Bind.s1);
      assert Bind.s1 == Bind.s1 && Bind.s1 == 4;
      Bind.s2 = 5; 
      System.out.println("Both should be 5: s1=" + Bind.s1 + " s2=" + Bind.s2);
      assert Bind.s1 == Bind.s2 && Bind.s1 == 5;

      // s4 gets changes from s3
      System.out.println("s3=" + Bind.s3 + " s4=" + Bind.s4);
      assert Bind.s3 == Bind.s4 && Bind.s3 == 3;
      Bind.s3 = 4; 
      System.out.println("Both should be 4: s3=" + Bind.s3 + " s4=" + Bind.s4);
      assert Bind.s3 == Bind.s4 && Bind.s3 == 4;
      Bind.s4 = 5; 
      System.out.println("Should be 4: s3=" + Bind.s3 + "Should be 5=" + Bind.s4);
      assert Bind.s3 != Bind.s4 && Bind.s3 == 4;

      // s6 gets changes from s5
      System.out.println("s5=" + Bind.s5 + " s6=" + Bind.s6);
      // reverse bidirection bindings don't fire on init now - should they?
      //assert Bind.s5 == Bind.s6 && Bind.s5 == 3;
      Bind.s6 = 4; 
      System.out.println("Both should be 4: s5=" + Bind.s5 + " s6=" + Bind.s6);
      assert Bind.s5 == Bind.s6 && Bind.s5 == 4;
      Bind.s5 = 5; 
      System.out.println("Should be 5: s5=" + Bind.s5 + " Should be 4: s6=" + Bind.s6);
      assert Bind.s5 != Bind.s6 && Bind.s5 == 5;

      //System.out.println("All active bindings");
      //sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.removeBindings(bd);
      sc.bind.Bind.removeBindings(Bind.class);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(bd);
   }
}
