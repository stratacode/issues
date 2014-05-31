public class Comp1 {
   int field1;
   int field2 = 2;

   public int staticField0 = 123;

   InnerDyn inner1 = new InnerDyn();

   public class InnerDyn {
      int subField1 = 11;
      int subField2;
      int subField3 := subField1 + subField2;
   }

   public int method1() {
      System.out.println("*** Comp1.method1: " + this);
      field1 = 1;
      System.out.println("*** 123= " + 123);
      return field1;
   }

   public void method2() {
      System.out.println("*** Comp1.method2: " + this);
      method1();
   }

}
