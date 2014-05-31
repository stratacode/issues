public class Dyn2 extends Comp1 {
   int field3 := field1 + field2;
   int field4;

   static int compField1 = 100;
   static int compField2 = 200;
   static int compField3 := compField1 + compField2;

   public int method1() {
      System.out.println("*** Dyn2.method1 - before: " + this);
      super.method1();
      System.out.println("*** Dyn2.method1 - after");
      field1 += 10;
      System.out.println("*** field3=" + field3);
   }

   public int method3() {
      System.out.println("*** Dyn2.method3: " + this);
      field3 += 3;
      System.out.println("*** field3=" + field3);
   }

   @sc.obj.MainSettings
   public static void main(String[] args) {
      Dyn2 d = new Dyn2();
      System.out.println("*** Hello from Dyn2 0=" + d.field1 + " 2=" + d.field2 + " 2=" + d.field3);
      d.method2();
      d.method3();
      System.out.println("*** Hello from Dyn2 11=" + d.field1 + " 2=" + d.field2 + " 16=" + d.field3);

      System.out.println("*** 100=" + compField1 + " 200=" + compField2 + " 300=" + compField3);
   }
}
