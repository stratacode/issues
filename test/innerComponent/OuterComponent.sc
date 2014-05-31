import sc.obj.MainSettings;

public class OuterComponent {


   @Component 
   public class InnerComponent {
      int i1 = 1;
      int i2 = 2;

   }

   object innerInst extends InnerComponent {

   }

   @MainSettings
   public static void main(String[] args) {
      OuterComponent oc = new OuterComponent();
      InnerComponent ic = oc.new InnerComponent();
      System.out.println(ic.i1);
      System.out.println(ic.i2);
   }
}
