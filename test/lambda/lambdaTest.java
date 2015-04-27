import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;

public class lambdaTest {
   public interface TestInterface {
      public void foo();
   }

   public interface TestOneArg {
      String val(String in);
   }

   public interface TestTwoArgs {
      String val(String in);
   }

   TestInterface x = () -> {};

   TestOneArg y = (String a) -> { return a + "y"; };
   TestOneArg z = (b) -> { return b + "z"; };
   TestOneArg zz = (c) -> c + "zz"; 

   @Test 
   void simpleLambda() {
      x.foo();
      assertTrue(y.val("foo").equals("fooy"));
      assertTrue(z.val("bar").equals("barz"));
      assertTrue(zz.val("fum").equals("fumzz"));
   }


   @Test 
   void localLambda() {
      TestOneArg ly = (String a) -> { return a + "y"; };
      TestOneArg lz = (b) -> { return b + "z"; };
      TestOneArg lzz = (c) -> c + "zz"; 
      
      assertTrue(ly.val("foo").equals("fooy"));
      assertTrue(lz.val("bar").equals("barz"));
      assertTrue(lzz.val("fum").equals("fumzz"));
   }

   boolean called = false;
   void testMethod(TestOneArg larg) {
      called = true;
      assertEquals(larg.val("inTestMethod"), "inTestMethod:methSuffix");
   }

   @Test 
   void methLambda() {
      testMethod((b) -> b + ":methSuffix");
      assertEquals(called, true);
   }

   boolean threadCalled = false;
   @Test
   void runnableTest() {
      Thread thread = new Thread(() -> threadCalled = true);
      thread.start();
      try {
         thread.join();
      }
      catch (InterruptedException exc) {
         System.err.println("*** failed test - thread interrupted: " + exc);
         assertTrue(false);
      }
      assertTrue(threadCalled);
   }

   boolean consumeCalled = false;

   @Test
   void funcTest() {
      Consumer<Integer>  c = (Integer x) -> { consumeCalled = true; System.out.println(x); };
    
      BiConsumer<Integer, String> b = (Integer x, String y) -> System.out.println(x + " : " + y);
      Predicate<String> p = (String s) -> s == null;

      assertTrue(p.test(null));
      assertTrue(!p.test("foo"));

      b.accept(3, "foo");
      c.accept(33);
      assertTrue(consumeCalled);
   }

   @Test
   void listTest() {
      List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
      int sum = list.stream().map(q -> q*q).reduce((q,r) -> q + r).get();
      // 140 = 1*1 + 2*2 + 3*3 + 4*4 + 5*5 + 6*6 + 7*7;
      assertTrue(sum == 140);
   }

   int field = 5;

   public interface Iface {
      boolean test(int x);
   }

   Iface iface = x -> x > this.field;

   @Test 
   void fieldTest() {
      assertTrue(iface.test(6));
      assertTrue(!iface.test(4));
      System.out.println("*** fieldTest ran");
   }

   @Test
   void exprTest() {
      // We've already tests fields, local variables
      // cast expressions
      TestOneArg ey = (TestOneArg) (String a) -> { return a + "y"; };
      TestOneArg ez = (TestOneArg) (b) -> { return b + "z"; };
      TestOneArg ezz = (TestOneArg) (c) -> c + "zz"; 

      assertTrue(ey.val("foo").equals("fooy"));
      assertTrue(ez.val("bar").equals("barz"));
      assertTrue(ezz.val("fum").equals("fumzz"));

      // assignment expression
      ey = (String a) -> { return a + "y"; };
      assertTrue(ey.val("bar").equals("bary"));

      // Return statement
      TestOneArg lr = testLambdaReturn();
      assertEquals(lr.val("foo"), "fooTM");

      // Argument to method
      String res = testLambdaParam((b) -> { return b + "PTest"; });
      assertEquals(res, "lambdaParamPTest");

      // Argument to constructor
      TestConstructor cstr = new TestConstructor((String a) -> { return a + "CTest"; });
      assertEquals(cstr.result, "lambdaConstCTest");

      // Array initializers
      TestOneArg[] arr = {(String a) -> a + "V0", (a) -> a + "V1"};
      int i = 0;
      for (TestOneArg oneArg:arr) {
         assertEquals("arTestV" + i, oneArg.val("arTest"));
         i++;
      }
   }

   TestOneArg testLambdaReturn() {
      return (b) -> b + "TM";
   }

   String testLambdaParam(TestOneArg oneArg) {
      return oneArg.val("lambdaParam"); 
   }

   class TestConstructor {
      String result;
      TestConstructor(TestOneArg arg) {
         result = arg.val("lambdaConst");
      }
   }

   // Test ternary/question mark operator lambda expressions
   @Test
   void testConditionsls() {
      for (int i = 0; i < 2; i++) {
         TestOneArg arg = i == 0 ? (p) -> p + "0" : (p) -> p + "1";
         System.out.println(arg.val("Cond") + "=" + "Cond" + i);
         assertEquals(arg.val("Cond"), "Cond" + i);
      }
   }

   // Tests nested lambdas
   @Test
   void testNestedLambda() {
      TestOneArg arg = (p) -> {
          TestOneArg innerArg = (q) -> q + "Inner";
          return innerArg.val(p + "Outer");
      };
      System.out.println(arg.val("Nested") + "=" + "NestedOuterInner");
      assertEquals(arg.val("Nested"), "NestedOuterInner");
   }


   @Test 
   void streamTest() {
      System.out.println("Expect a1 twice");
      Arrays.asList("a1", "a2", "a3")
          .stream()
          .findFirst()
       .ifPresent(System.out::println);  // a1

      Stream.of("a1", "a2", "a3")
          .findFirst()
          .ifPresent(System.out::println);  // a1

      System.out.println("Expect 5.0");
      Arrays.stream(new int[] {1, 2, 3})
          .map(n -> 2 * n + 1)
          .average()
          .ifPresent(System.out::println);  // 5.0
   }
}
