@MainInit
object O1 {
   object innerO1 extends C1 {
   }

   O1() {
      System.out.println("O1 created");
      System.out.println(innerO1.a + " " + innerO1.b + " " + innerO1.c + " " + innerO1.d);

      System.out.println("*** before synchronized");
      synchronized (this) {
        System.out.println("*** in synchronized");
      }
      System.out.println("*** after synchronized");

      innerO1.foo();

      System.out.println("*** after foo");
   }
}
