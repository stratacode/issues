public class ModComp2 {
   int foo;
   int bar = 2;

   public void method1() {
      System.out.println("*** foo=" + foo + " bar=" + bar);
   }

   @sc.obj.MainSettings
   public static void main(String[] args) {
      ModComp2 d = new ModComp2();
      d.method1();
   }
}
