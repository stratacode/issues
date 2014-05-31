
public class CBind {
   public int b1 = 3;
   public boolean b2 := b1 == 3;

   public int b3 = 3;
   public boolean b4 := b2 && b3 == 3;

   public int b5 = 3;
   public boolean b6 := b4 && b2;

   static public String s1 = "s1";
   static public String s2 = "s2";
   static public boolean s3 := s1.equals("s1") && s2.equals("s2");

   public Integer selectedAnswerIndexObj = new Integer(1);
   public boolean selected := (selectedAnswerIndexObj.intValue() == 0);

   public Integer selectedAnswerIndexNoWrap = new Integer(1);
   public boolean selectedNoWrap := (selectedAnswerIndexNoWrap == 0);

   public boolean containsString(String base, String sub) {
      if (base == null || sub == null)
         return base == sub;
      return base.contains(sub);
   }

   public String x = null;
   public String a = null;
   public boolean choice := x != null && a != null && containsString(a, x);
   public boolean choice2 := x != null && a != null && x.contains(a);

   @MainSettings(test=true)
   public static void main(String[]args) {
      CBind bd = new CBind();

      // Bi directional
      System.out.println("true=" + bd.b2 + " true=" + bd.b4);
      assert bd.b2 == true && bd.b4 == true;
      bd.b1 = 4; 
      System.out.println("false=" + bd.b2 + " false=" + bd.b4);
      assert bd.b2 == false && bd.b4 == false;

      System.out.println("true=" + s3);
      s2 = "s2new";
      // TODO: expressions do not trigger changes when an event occurs on "this"
      //System.out.println("false=" + s3);

      System.out.println("false = " + bd.selected);
      bd.selectedAnswerIndexObj = 0;
      // Expressions do not trigger changes when an event occurs on "this"
      //System.out.println("TOFIX = " + bd.selected);

      System.out.println("false = " + bd.selectedNoWrap);
      bd.selectedAnswerIndexNoWrap = 0;
      System.out.println("true = " + bd.selectedNoWrap);

      if (bd.choice || bd.choice2) 
         System.out.println("FAILED - choice should be false");
      bd.x = "foo";
      if (bd.choice || bd.choice2) 
         System.out.println("FAILED - choice should be false");
      bd.a = "foo";
      if (!bd.choice || !bd.choice2)
         System.out.println("FAILED - choice should be true");
      else
         System.out.println("choice test passed");

      //System.out.println("All active bindings");
      //sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.removeBindings(bd);
      sc.bind.Bind.removeBindings(CBind.class);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(bd);
   }
}
