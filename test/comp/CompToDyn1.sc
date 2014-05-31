public class CompToDyn1 {
   ManDyn1 dyn1 = new ManDyn1();

   int cField1 = dyn1.field1;
   int cField2 := dyn1.field3 * 2;

   @sc.obj.MainSettings
   public static void main(String[] args) {
      CompToDyn1 cd = new CompToDyn1();
      System.out.println("*** compToDyn1 10=" + cd.cField1 + " 24=" + cd.cField2);
   }
}
