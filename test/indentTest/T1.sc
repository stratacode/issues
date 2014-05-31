class T1 {
   int foo;

   foo = 3;

   void foo() {
   }

   @Bindable
   int bar;

   class Inner {

      //void bar() {
      //}


      @Bindable
      int fum;

      fum = 3;
   }

   foo = 4;
}
