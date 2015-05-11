import java.math.BigInteger;
import java.util.List;
import java.util.LinkedList;

class ParallelList {
   static List<BigInteger> list = new LinkedList<>();

   @Test
   void runTest() {
      createList();

      long start = System.currentTimeMillis();

      System.out.println(list.parallelStream().
              filter(n -> n.mod(new BigInteger("2")).equals(BigInteger.ZERO)).
                  mapToLong(BigInteger::longValue).sum());

      long end = System.currentTimeMillis();

      System.out.println("Time taken using Java 8:  " + (end - start) + " ms");
   }

   private static void createList() {
      for (int i = 0; i < 100000; i++) {
         list.add(new BigInteger(String.valueOf(i)));
      }
   }
}
