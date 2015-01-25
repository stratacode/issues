class Outer {

  class Outer1 {
     String getString() {
        return Outer.this.getString() + ":Outer1";
     }

     class Intermediate {

        void init() {
           // Here we skip the class Intermediate to get the outer class for ExtOuter1
           ExtOuter1 ext1 = new ExtOuter1();
           System.out.println(ext1.getString());
           assertTrue(ext1.getString().equals("Outer:Outer1:ExtOuter1"));
        }


        String getString() {
           return Outer.this.getString() + ":Intermediate";
        }
     }


     class ExtOuter1 extends Outer1 {
        String getString() {
           return Outer1.this.getString() + ":ExtOuter1";
        }

     }

     void init() {
        Intermediate inter = new Intermediate();
        inter.init();
     }

  }

  String getString() {
     return "Outer";
  }

  void init() {
     Outer1 o1 = new Outer1();
     o1.init();
  }

  @Test
  void doTest() {
     init();
     System.out.println("Test passed");
  }
}
