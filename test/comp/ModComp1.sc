public class ModComp1 {

   int foo;
   int bar = 2;

   public void method1() {
      System.out.println("*** foo=" + foo + " bar=" + bar);
   }

   @sc.obj.MainSettings
   public static void main(String[] args) {
      ModComp1 d = new ModComp1();
      d.method1();
   }
}
