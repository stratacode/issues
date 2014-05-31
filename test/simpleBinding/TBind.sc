
public class TBind {
   public short b1 = 3;
   public int b2 := b1 + 1;

   public int b3 = 3;
   public float b4 := b2 * 2 + b3; 

   public int b5 = 3;
   public double b6 := b4 / (b2-2); 

   static public String s1 = "s1";
   static public int s2 = 2;
   static public String s3 := s1 + s2;

   // should get an error...
   public boolean bool1 = true;
   //public int bool2 := !bool1; //xx

   public short a1 = 8;
   public int a2 :=: a1 + 4;  // 14 = x + 4  x = 14 - 4

   public int a3 = 8;
   public float a4 :=: a3 / 4;  

   public int a5 = 4;
   public double a6 :=: 8 / a5;

   public int a7 = 4;
   public long a8 :=: 8 - a7;

   @MainSettings(test=true)
   public static void main(String[]args) {
      TBind bd = new TBind();

      System.out.println("*** 12=" + bd.a2);
      bd.a1 = 9;
      System.out.println("*** 13=" + bd.a2);
      bd.a2 = 14;
      System.out.println("*** 10=" + bd.a1);

      System.out.println("*** 2=" + bd.a4);
      bd.a3 = 12;
      System.out.println("*** 3=" + bd.a4);
      bd.a4 = 4;
      System.out.println("*** 16=" + bd.a3);

      System.out.println("*** 2=" + bd.a6);
      bd.a5 = 2;
      System.out.println("*** 4=" + bd.a6);
      bd.a6 = 4;
      System.out.println("*** 2=" + bd.a5);

      System.out.println("*** 4=" + bd.a8);
      bd.a7 = 9;
      System.out.println("*** -1=" + bd.a8);
      bd.a8 = 14;
      System.out.println("*** -6=" + bd.a7);

      System.out.println("s12=" + s3);
      s2 = 222;
      System.out.println("s1222=" + s3);

      //System.out.println("All active bindings");
      //sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.removeBindings(bd);
      sc.bind.Bind.removeBindings(TBind.class);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(bd);
   }
}
