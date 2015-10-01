public class Cast {

   public Comparable getValue() {
      return 3;
   }

   public void foo() {
      int ivar = 333;
      ivar -= 3.0;

      double dvar = 666.0;

      // Error - loss of precision
      //ivar = dvar;
      ivar %= 3.0;

      // Error - 
      //ivar <<= 3.1;
   }
}
