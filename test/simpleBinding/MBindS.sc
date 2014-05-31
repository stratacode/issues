
public class MBindS {
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

   //String s5 := MBindS.concat(s1, MBindS.concat(s1,s2));

   //String s6 := concat(concat(s1, MBindS.concat(s1,s2)), childObj.c2);
   String s6 := concat(s1, childObj.c2);

   public static String concat(String arg1, String arg2) {
      return arg1 + arg2;
   }

   //public int b2 := timesTwo(b1);

   //public int b3 := timesTwo(b0); // Starts out null

   @MainSettings(test=true)
   public static void main(String[]args) {
      MBindS bd = new MBindS();

   /*
      System.out.println("*** should be 6: " + bd.b2);
      bd.b1 = 4;
      System.out.println("*** should be 8: " + bd.b2);

      System.out.println("*** should be 0: " + bd.b3);
      
      bd.b0 = 5;
      System.out.println("*** should be 10: " + bd.b3);

      System.out.println("*** Should be 'hello world': " + bd.s3);
      System.out.println("*** Should be 'hello hello world': " + bd.s4);
      */
      //System.out.println("*** Should be 'hello hello world': " + bd.s5);
      System.out.println("*** Should be 'hello !!!': " + bd.s6);
      bd.s1 = "goodbye ";
      /*
      System.out.println("*** Should be 'goodbye goodbye world': " + bd.s5);
      System.out.println("*** Should be 'goodbye world': " + bd.s3);
      System.out.println("*** Should be 'goodbye goodbye world': " + bd.s4);
      */
      bd.childObj.c2 = "???";
      //System.out.println("*** Should be 'goodbye goodbye world???': " + bd.s6);
      System.out.println("*** Should be 'goodbye ???': " + bd.s6);
   }
}
