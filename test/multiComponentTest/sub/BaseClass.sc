BaseClass {
   f1 = 11;

   int f3 = 333;

   protected int f2 = 2222;

   BaseClass() {}

   BaseClass(int p1, int p2) {
      super(p1, p2);
      System.out.println("*** in overridden constructor");
   }

   public BaseClass(int p1, int p2, int p3) {
      this(p1, p2);
      System.out.println("*** in added constructor");
   }

   //@FoobarAnnotation(name3="value3")
   @SuppressWarnings("unchecked")
   void foobar() {
      super.foobar();
      System.out.println("*** in overridden foo after calling super()");
   }

   @SuppressWarnings("unchecked")
   private void addedMethod() {
      this.foobar(); // There's a question here and above in the this(p1, p2) for the BaseClass(int p1, int p2, int p3).  Should it be super or this?   
                     // Originally we had "super" but changed the code in IdentifierExpression super's transform so it only renames methods if the original method
                     // modifies another method.  The old way gave you more flexibility - being able to call the modified layer's version of the method but the
                     // new way
      System.out.println("*** in addedMethod after super.foobar()");
   }

   {
      f3 = 333333;
   }

   @SuppressWarnings("unchecked")
   InnerClass {
      if1 = 11;
      if2 = 22;
      int if3 = 33;

      void initMethod(int p1) {
         super.initMethod(p1);
         System.out.println("*** in overridden init method");
      }
   }
}
