@MainInit
class Sub extends Base {
   void foo() {
      System.out.println("** in Sub.foo - calling Base.foo");
      super.foo();
      System.out.println("** after calling Base.foo");
   }
   Sub() {
      System.out.println("In Sub");
      super();

      foo();
   }
}
