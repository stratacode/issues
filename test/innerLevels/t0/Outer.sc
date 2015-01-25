class Outer {

  class Outer1 {
     String toString() {
        return Outer.this + ":Outer1";
     }

     class Intermediate {

        void init() {
           // Here we skip the class Intermediate to get the outer class for ExtOuter1
           ExtOuter1 ext1 = new ExtOuter1();
           System.out.println(ext1.toString());
           assertTrue(ext1.toString().equals("Outer:Outer1:ExtOuter1"));
        }


        String toString() {
           return Outer.this + ":Intermediate";
        }
     }


     class ExtOuter1 extends Outer1 {
        String toString() {
           return Outer1.this + ":ExtOuter1";
        }

     }

     void init() {
        Intermediate inter = new Intermediate();
        inter.init();
     }

  }

  String toString() {
     return "Outer";
  }

  void init() {
     Outer1 o1 = new Outer1();
     o1.init();
  }

  @Test
  void doTest() {
     Outer outer = new Outer();
     outer.init();
     System.out.println("Test passed");
  }
}
