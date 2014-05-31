dynamic public class ManDyn1 {
   int field1 = 10;
   int field2 = 2;
   int field3 := field1 + field2;

   InnerDyn inner1 = new InnerDyn();

   public class InnerDyn {
      int subField1 = 11;
      int subField2;
      int subField3 := subField1 + subField2;
   }

   public void method1() {
      field1 = 1;
      inner1.subField2 = 22;
   }

   public void method2() {
      method1();
   }

   @sc.obj.MainSettings
   public static void main(String[] args) {
      ManDyn1 d = new ManDyn1();
      d.method2();
      System.out.println("*** Hello from ManDyn1 1=" + d.field1 + " 2=" + d.field2 + " 3=" + d.field3);
      System.out.println("*** 11=" + d.inner1.subField1 + " 22=" + d.inner1.subField2 + " 33=" + d.inner1.subField3);
   }
}
