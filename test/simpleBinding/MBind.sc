
public class MBind {
   public int b0;
   public int b1 = 3;

   object childObj {
      int c0;
      int c1 = 1;

      String c2 = "!!!";

      int c4 :=: c1;
   }

   public int timesTwo(int arg) {
      return arg * 2;
   }

   String s1 = "hello ";
   String s2 = "world";

   String s3 := concat((String) s1, (String) s2);

   String s4 := concat(s1, concat(s1,s2));

   String s5 := MBind.concat(s1, MBind.concat(s1,s2));

   String s6 := concat(concat(s1, MBind.concat(s1,s2)), childObj.c2);

   String s7 = "orig";
   s7 =: s7changed((String)s7);

   public void s7changed(String arg) {
      System.out.println("*** s7 changed with: " + arg);
   }

   Object s8 = "orig";
   s8 =: s8changed((String)s8);

   String someVal = "stuff";

   int i8 = 11;
   i8 =: i8changed(someVal);

   public void i8changed(String arg) {
      System.out.println("*** i8 changed with: " + arg);
   }

   int i9 = 11;
   i9 =: i9changed(concat("foo", someVal));

   public void i9changed(String arg) {
      System.out.println("*** i9 foostuff=" + arg);
   }

   public static void s8changed(String arg) {
      System.out.println("*** s8 changed with: " + arg);
   }

   public static String concat(String arg1, String arg2) {
      return arg1 + arg2;
   }

   public int b2 := timesTwo(b1);

   public int b3 := timesTwo(b0); // Starts out null

   @MainSettings(test=true)
   public static void main(String[]args) {
      MBind bd = new MBind();

      System.out.println("*** should see s7 changed with: newvalue for s7");
      bd.s7 = "newvalue for s7";

      System.out.println("*** should see s8 changed with: newvalue for s8");
      bd.s8 = "newvalue for s8";

      System.out.println("*** should see i8 changed with: stuff for i8");
      bd.i8 = 22;

      bd.i9 = 23;

      System.out.println("*** should be 6: " + bd.b2);
      bd.b1 = 4;
      System.out.println("*** should be 8: " + bd.b2);

      System.out.println("*** should be 0: " + bd.b3);
      
      bd.b0 = 5;
      System.out.println("*** should be 10: " + bd.b3);

      System.out.println("*** Should be 'hello world': " + bd.s3);
      System.out.println("*** Should be 'hello hello world': " + bd.s4);
      System.out.println("*** Should be 'hello hello world': " + bd.s5);
      System.out.println("*** Should be 'hello hello world!!!': " + bd.s6);
      bd.s1 = "goodbye ";
      System.out.println("*** Should be 'goodbye world': " + bd.s3);
      System.out.println("*** Should be 'goodbye goodbye world': " + bd.s4);
      System.out.println("*** Should be 'goodbye goodbye world': " + bd.s5);
      bd.childObj.c2 = "???";
      System.out.println("*** Should be 'goodbye goodbye world???': " + bd.s6);

      //System.out.println("All active bindings");
      //sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.removeBindings(bd);
      sc.bind.Bind.removeBindings(bd.childObj);
      sc.bind.Bind.removeBindings(MBind.class);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.printBindings(bd.childObj);
   }
}
