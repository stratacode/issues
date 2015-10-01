//package langTest;
import java.util.ArrayList;
import java.util.Arrays;


public class OverVar {
   static int uniCt = 0;
   static int varCt = 0;

   public static<T> ArrayList<T> of(T... values) {
      System.out.println("*** in var args");
      varCt++;
      return new ArrayList<T>(Arrays.asList(values));
   }

   public static<T> ArrayList<T> of(T t) {
      System.out.println("*** in uni arg");
      uniCt++;
      ArrayList<T> res = new ArrayList<>();
      res.add(t);
      return res;
   }

   public static<T> ArrayList<T> ofU(T t) {
      System.out.println("*** in uni arg (only)");
      uniCt++;
      ArrayList<T> res = new ArrayList<>();
      res.add(t);
      return res;
   }

   @sc.obj.MainSettings
   public static void main(String[] args) {
      ArrayList<Integer> res; 
      Integer[] arr = new Integer[] {1, 2};
      // Calls uni-arg
      res = OverVar.of(1);
      System.out.println(res);

      if (uniCt != 1)
         System.err.println("*** Failed!");

      // Calls var-arg - res is ArrayList<Integer> which only matches var-arg after resolving type params
      res = OverVar.of(arr);
      System.out.println(res);

      if (varCt != 1)
         System.err.println("*** Failed!");

      int[] arr2 = new int[] {1, 2, 3};
      // Calls uni-arg (if it's defined) - it matches both uni and var but when it matches 'var' is matches as a single arg
      ArrayList<int[]> ares = OverVar.of(arr2);
      System.out.println(ares);

      if (uniCt != 2)
         System.err.println("*** Failed!");

      // Calls var-arg - only var arg matches
      ArrayList<Integer> ires = OverVar.of(arr);
      System.out.println(ires);

      if (varCt != 2)
         System.err.println("*** Failed!");

      // Calls uni-arg - both match but since arr2 is a primitive array, not an array of objects, we pick the uni
      Object ores = OverVar.of(arr2);
      System.out.println(ores);

      if (uniCt != 3)
         System.err.println("*** Failed!");

      // Calls var-arg - both match but since arr is an array we pick the var args one
      Object ores2 = OverVar.of(arr);
      System.out.println(ores2);

      if (varCt != 3)
         System.err.println("*** Failed!");

      // Calls uni-arg - both match but since 3 is not an array we pick the uni arg
      Object ores3 = OverVar.of(3);
      System.out.println(ores3);

      if (uniCt != 4)
         System.err.println("*** Failed!");

      // This does not compile when the var args method is defined.  It matches the varArgs version and then
      // has incompatible bounds.  If you comment out the var args one, then it compiles by matching the other one
      ArrayList<Integer[]> aores = OverVar.ofU(arr);
      System.out.println(aores);

      if (uniCt != 5)
         System.err.println("*** Failed!");

   }
}
