public class NestedAssign {
   @Bindable
   int a = 1;
   @Bindable
   float b = 1.0f;
   float b2 = 2.0f;
   @Bindable
   Integer c = 1;
   @Bindable
   boolean d = true;
   @Bindable
   Boolean e = true;

   @Bindable
   Object f = new Object();

   @Test
   public void simpleNested() {
      a = c = 2;
      assertTrue(a == 2 && c == 2);
      b = b2 = 3.0f;
      assertTrue(b == 3.0f && b2 == 3.0f);
      assertTrue((a = 4) == 4);
   }

   @Bindable
   int x = 1;
   
   @Test
   public void nestedExpression() {
      a = 1;
      intMethod(x = a++, 1);
      assertTrue(x == 1);
      assertTrue(a == 2);

      a = 1;
      intMethod(x = (a++), 1);
      assertTrue(x == 1);
      assertTrue(a == 2);

      a = 1;
      intMethod(x = ++a * 4, 8);
      assertTrue(a == 2);
   }

   object inner {
      @Bindable
      int x = 22;
      int y = 22;
      int z = 33;
      int[] arr = {1, 2, 3};

      float f1 = 2.5f;
      double d1 = 5.0;
   }

   @Test
   public void nestedPathName() {
      inner.x = inner.y = 111;
      assertTrue(inner.x == 111 && inner.y == 111);
      inner.y = inner.x = 222;
      assertTrue(inner.x == 222 && inner.y == 222);
      assertTrue((inner.z = inner.x = 333) == 333 && inner.x == 333);

      assertTrue((inner.arr[0] = 111) == 111);
      assertTrue((inner.arr[0] = inner.arr[1] = 222) == 222);
   }

   @Test
   public void unaryParen() {
      assertTrue(((inner.x) = 123) == 123);
      assertTrue(((inner.x) = 23) == 23);

      assertTrue(((inner.arr[0]) = 345) == 345);

      assertTrue(((this.inner).f1 = 2.5f) == 2.5f);
      assertTrue((this.inner.f1 = 3.5f) == 3.5f);
   }

   public void intMethod(int foo, int expected) {
      assertTrue(foo == expected);
   }

   @Bindable int convertedProperty = 123;
   public int testReturnAssign() {
      return convertedProperty = 245;
   }

   @Test 
   public void testConvertedProperty() {
      assertTrue(testReturnAssign() == 245);
   }

}
