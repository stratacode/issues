@Deprecated
@Component
public class BaseClass {
   // should be 11
   int f1;
   public int f2;

   @Deprecated
   public BaseClass(int p1, int p2) {
      f1 = p1;
      f2 = p2;
      System.out.println("**** in BaseClass constructor ");
   }

   //@FoobarAnnotation(name1="value1", name2="value2")
   @Deprecated
   public void foobar() {
      System.out.println("**** in foobar");
   }

   //@InnerClassAnnotation(name3="value3")
   @Deprecated
   public class InnerClass {
      int if1 = 10;
      int if2; 

      @Deprecated
      public void initMethod(int p1) {
         System.out.println("*** in initMethod");
      }
   }
}
