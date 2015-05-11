import java.util.function.Consumer;

public class LambdaScopeTest {

    public int x = 0;

    class FirstLevel {

        public int x = 1;

        void methodInFirstLevel(int x) {
            
            // The following statement causes the compiler to generate
            // the error "local variables referenced from a lambda expression
            // must be final or effectively final" in statement A:
            //
            // x = 99;
            
            Consumer<Integer> myConsumer = (y) -> 
            {
                System.out.println("x = " + x); // Statement A
                System.out.println("y = " + y);
                System.out.println("this.x = " + this.x);
                System.out.println("LambdaScopeTest.this.x = " +
                    LambdaScopeTest.this.x);
                assertEquals(this.x, 1);
                assertEquals(LambdaScopeTest.this.x, 0);
            };

            myConsumer.accept(x);

        }
    }

    @Test
    public void test() {
        LambdaScopeTest.FirstLevel fl = new FirstLevel();
        fl.methodInFirstLevel(23);
    }
}
