
import sc.util.HashMap;

public class QBind {

   int v1, v2;
   int trigger =: v1 == v2 ? System.out.println("equals") : System.out.println("not equals");

   boolean toggle = false;
   int change =: toggle ? v1 : v2;

   @MainSettings(test=true)
   public static void main(String[]args) {
      QBind bd = new QBind();

      System.out.println("equals?");
      bd.trigger = 1;
      bd.v1 = 1;
      System.out.println("not equals?");
      bd.trigger = 2;

      bd.change = 33;
      System.out.println("33=" + bd.v2);
      bd.change = 44;
      System.out.println("44=" + bd.v2);
      bd.toggle = true;

      bd.change = 55;
      System.out.println("55=" + bd.v1);

      //System.out.println("All active bindings");
      //sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.removeBindings(bd);
      sc.bind.Bind.removeBindings(QBind.class);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(bd);
   }
}
