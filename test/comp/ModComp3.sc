public class ModComp3 extends Comp1 {
   int field3;
   int field4 = 4;
   int field5 = 5;

   public int method1() {
      super.method1();
      System.out.println("*** field1=" + field1 + " field2=" + field2 + " field3=" + field3 + " field4=" + field4 + " field5=" + field5);
      return field1;
   }

   @sc.obj.MainSettings
   public static void main(String[] args) {
      ModComp3 d = new ModComp3();
      d.method1();
   }
}
