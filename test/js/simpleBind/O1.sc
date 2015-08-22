@MainInit
object O1 {
  object innerO1 extends C1 {
  }

  O1() {
    System.out.println("O1 created");
    System.out.println(innerO1.a + " " + innerO1.b + " " + innerO1.c);
    System.out.println(C1.vsa + " " + C1.vsb + " " + C1.vsc);
    System.out.println("complete");
  }
}
