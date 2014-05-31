
public class NBind {
   int b1 = 333;
   public childObj b0 := new childObj(b1);

   int b2 = 555;
   public staticChildObj b3 := new staticChildObj(b2);

   public class childObj {
      public childObj(int arg1) {
         System.out.println("*** childObj created with: " + arg1);
      }
   }

   public static class staticChildObj {
      public staticChildObj(int arg1) {
         System.out.println("*** staticChildObj created with: " + arg1);
      }
   }

   @MainSettings(test=true)
   public static void main(String[]args) {
      NBind bd = new NBind();

      bd.b1 = 444;

      bd.b2 = 666;
   }
}
