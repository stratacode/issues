
public class HBind3 {

   public class Type1 {
      public int b1 = 3;
   }

   public Type1 obj1 = new Type1();

   public object obj2 extends Type1 {
      b1 = 30;
   }

   public int b3 :=: obj1.b1;

   @MainSettings(test=true)
   public static void main(String[]args) {
      HBind3 bd = new HBind3();

      bd.obj1 = bd.obj2;
   }
}
