@MainInit
object OBJ1 implements IF1 {
   void foo() {
      System.out.println("*** true=" + (this instanceof OBJ1));
      System.out.println("*** false=" + (this instanceof java.lang.System));
      System.out.println("*** true=" + (this instanceof IF1));
   }
   OBJ1() {
      foo();
      IF1 v1 = this;
      v1.foo();
   }
}
