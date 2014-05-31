
public class ABind {
   public int b1 = 3;
   public int b2 := b1 + 1;

   public int b3 = 3;
   public int b4 := b2 * 2 + b3;

   public int b5 = 3;
   public int b6 := b4 / (b2-2);

   static public String s1 = "s1";
   static public String s2 = "s2";
   static public String s3 := s1 + s2;

   public boolean bool1 = true;
   public boolean bool2 := !bool1;

   public int a1 = 8;
   public int a2 :=: a1 + 4;  // 14 = x + 4  x = 14 - 4

   public int a3 = 8;
   public int a4 :=: a3 / 4;

   public int a5 = 4;
   public int a6 :=: 8 / a5;

   public int a7 = 4;
   public int a8 :=: 8 - a7; // 14 = 8 - x ;   -6

   public int a9 = 4;
   public int a10 :=: a9 - 2;

   public int a11 = 4;
   public int a12 :=: 8 * a11; // 64 = 8 * x ;  8 

   public int a13 = 4;
   public int a14 :=: a13 * 2;

   public int a15 = 8;
   public int a16 :=: 4 + a15;  

   public int a17 = 8;
   public int a18 :=: 4 + 3 + a17 + 1;  

   int switched := (bool2 ? b6 : b4);

   @Bindable(manual=true)
   public int noEventProp1 = 5;
   @Bindable(manual=true)
   public int noEventProp2 = 10;

   int refreshResult := 5 + noEventProp1 + (3 * noEventProp2);

   @MainSettings(test=true)
   public static void main(String[]args) {
      ABind bd = new ABind();

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

      System.out.println("*** 2=" + bd.a10);
      bd.a9 = 9;
      System.out.println("*** 7=" + bd.a10);
      bd.a10 = 14;
      System.out.println("*** 16=" + bd.a9);

      System.out.println("*** 32=" + bd.a12);
      bd.a11 = 2;
      System.out.println("*** 16=" + bd.a12);
      bd.a12 = 64;
      System.out.println("*** 8=" + bd.a11);

      System.out.println("*** 8=" + bd.a14);
      bd.a13 = 8;
      System.out.println("*** 16=" + bd.a14);
      bd.a14 = 32;
      System.out.println("*** 16=" + bd.a13);

      System.out.println("*** 12=" + bd.a16);
      bd.a15 = 9;
      System.out.println("*** 13=" + bd.a16);
      bd.a16 = 14;
      System.out.println("*** 10=" + bd.a15);

      System.out.println("*** 16=" + bd.a18);
      bd.a17 = 10;
      System.out.println("*** 18=" + bd.a18);
      bd.a18 = 20;
      System.out.println("*** 12=" + bd.a17);

      // Bi directional
      System.out.println("4=" + bd.b2 + " 11=" + bd.b4 + " 5=" + bd.b6 + " 11=" + bd.switched);
      // Note: JS will not truncate to int
      assert bd.b2 == 4 && bd.b4 == 11 && (bd.b6 >= 5 && bd.b6 < 6) && bd.switched == 11;
      bd.b1 = 4; 
      System.out.println("5=" + bd.b2 + " 13=" + bd.b4 + " 4=" + bd.b6 + " 13=" + bd.switched);
      // Note: JS will not truncate to int
      assert bd.b2 == 5 && bd.b4 == 13 && bd.b6 >= 4 && bd.b6 < 5 && bd.switched == 13;

      System.out.println("s1s2=" + s3);
      s2 = "s2new";
      System.out.println("s1s2new=" + s3);

      System.out.println("false=" + bd.bool2);
      bd.bool1 = false;
      System.out.println("true=" + bd.bool2 + " 4=" + bd.switched);
      assert bd.bool2 && bd.switched >= 4 && bd.switched < 5;

      System.out.println("40=" + bd.refreshResult);
      bd.noEventProp1 = 10;
      bd.noEventProp2 = 20;
      System.out.println("40=" + bd.refreshResult);
      sc.bind.Bind.refreshBindings(bd);
      System.out.println("75=" + bd.refreshResult);

      //System.out.println("All active bindings");
      //sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.removeBindings(bd);
      sc.bind.Bind.removeBindings(ABind.class);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(bd);

   }
}
