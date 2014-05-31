
import sc.obj.Component;

// Hello
@Component
object Component1 {
   int bar = 3;
   //Component3 c31 = Component3;

   public void init() {
      System.out.println("*** custom init method");
   }

   public void start() {
      System.out.println("*** custom start method");
   }
}
