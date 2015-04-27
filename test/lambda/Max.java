import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collection;

class Max {
   public static <A extends Comparable<A>> A max (Collection<A> xs) { 
      Iterator<A> xi = xs.iterator(); 
      A w = xi. next(); 
      while (xi.hasNext()) { 
         A x = xi.next(); 
        if (w.compareTo(x) < 0) w = x; 
      } 
      return w; 
   } 

   @Test
   void runTest() {
      LinkedList<Long> list = new LinkedList<Long>(); 
      list.add(0L);  
      list.add(1L); 
      Long y = Max.max(list) ; 
   }
}
