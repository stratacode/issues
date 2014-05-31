
import sc.util.HashMap;

public class HMBind {

   HashMap<String,Object> map = new HashMap<String,Object>();

   String key = "k1";
   Object inst :=: map.get(key);

   String key2 = "k1";
   Object inst2 :=: map.get(toUpperCase(key2));

   String toUpperCase(String str) {
      return str.toUpperCase();
   }

   @MainSettings(test=true)
   public static void main(String[]args) {
      HMBind bd = new HMBind();

      System.out.println("null=" + bd.inst);
      bd.inst = "v1";
      System.out.println("v1=" + bd.inst);
      System.out.println("v1=" + bd.map.get(bd.key));
      bd.key = "k2";
      System.out.println("null=" + bd.inst);
      System.out.println("null=" + bd.map.get(bd.key));
      bd.inst = "v2";
      System.out.println("v2=" + bd.inst);
      System.out.println("v2=" + bd.map.get(bd.key));

      bd.key = "k1";
      System.out.println("v1=" + bd.inst);
      System.out.println("v1=" + bd.map.get(bd.key));

      bd.inst2 = "V1";
      System.out.println("V1=" + bd.inst2);
      System.out.println("V1=" + bd.map.get(bd.toUpperCase(bd.key2)));

      bd.key2 = "k4";
      System.out.println("null=" + bd.inst2);
      System.out.println("null=" + bd.map.get(bd.toUpperCase(bd.key2)));

      bd.inst2 = "V3";
      System.out.println("V3=" + bd.inst2);
      System.out.println("V3=" + bd.map.get(bd.toUpperCase(bd.key2)));

      //System.out.println("All active bindings");
      //sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.removeBindings(bd);
      sc.bind.Bind.removeBindings(HMBind.class);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(bd);
   }
}
