// Hello from BaseClass.java
@Component
BaseClass {
   f1 = 1011;
   f2 = 1022;
   //f3 = 1033;

   // This is the foobar method
   void foobar() {
      System.out.println("*** Hello before super foobar");
      super.foobar();
   }

   // This is the added method
   public void addedMethod() {
      System.out.println("*** made the method public");
   }

   // This is the Inner class
   InnerClass {
      if1 = 1011;  
      if2 = 1022;
      if3 = 1033;
      int if4 = 1044;;

      void initMethod(int p1) {
         System.out.println("*** subsub init method called before super:");
         super.initMethod(p1);
      }
   }
}
