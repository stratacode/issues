public class Unary {
   @Bindable
   int a = 1;
   @Bindable
   float b = 1.0f;
   @Bindable
   Integer c = 1;
   @Bindable
   boolean d = true;
   @Bindable
   Boolean e = true;

   @Test
   public void simpleUnary() {
      a++;
      assertTrue(a == 2);
      b++;
      assertTrue(b == 2.0);
      c++;
      assertTrue(c == 2);
      --a;
      assertTrue(a == 1);
      --b;
      assertTrue(b == 1.0);
      --c;
      assertTrue(c == 1);

      int ld = +a + +c;
      assertTrue(ld == 2);
      int le = -a - -c;
      assertTrue(le == 0);
      int lf = ~a; // LOGICAL NOT
      assertTrue(lf == -2);
      boolean g = !d;
      assertTrue(!g);
      boolean h = !e;
      assertTrue(!h);
   }

   @Bindable
   int x = 1;
   
   @Test
   public void unaryExpression() {
      intMethod(x++, 1);
      assertTrue(x == 2);
      intMethod(x++ * 4, 8);
      assertTrue(x == 3);
      intMethod(++x * 4, 16);
      assertTrue(x == 4);
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

   object inner2 {
      @Bindable
      int x = 22;
      int y = 22;
      int z = 33;
      int[] arr = {1, 2, 3};

      float f1 = 2.5f;
      double d1 = 5.0;
   }

   @Test
   public void unaryPathName() {
      inner2.x++;
      assertTrue(inner2.x == 23);
      assertTrue(inner2.x++ == 23);
      assertTrue(inner2.x == 24);

      inner2.y++;
      assertTrue(inner2.y == 23);
      assertTrue(inner2.y++ == 23);
      assertTrue(inner2.y == 24);

      --inner2.z;
      assertTrue(inner2.z == 32);
      assertTrue(--inner2.z == 31);

      assertTrue(inner2.arr[0]++ == 1);
      assertTrue(inner2.arr[0] == 2);
      assertTrue(--inner2.arr[1] == 1);

      assertTrue(this.inner2.f1++ == 2.5f);
      assertTrue(this.inner2.f1 == 3.5f);
      assertTrue(this.inner2.d1++ == 5.0);
      assertTrue(this.inner2.d1 == 6.0);
   }

   @Test
   public void unaryParen() {
      inner.x++;
      assertTrue(inner.x == 23);
      assertTrue((inner.x)++ == 23);
      assertTrue(inner.x == 24);

      inner.y++;
      assertTrue(inner.y == 23);
      assertTrue((inner.y++) == 23);
      assertTrue(inner.y == 24);

      --inner.z;
      assertTrue((inner).z == 32);
      assertTrue((--inner.z) == 31);

      assertTrue((inner.arr[0])++ == 1);
      assertTrue(inner.arr[0] == 2);
      assertTrue(--(inner.arr[1]) == 1);

      assertTrue((this.inner).f1++ == 2.5f);
      assertTrue(this.inner.f1 == 3.5f);
      assertTrue((this.inner.d1)++ == 5.0);
      assertTrue(((this.inner.d1)) == 6.0);
   }

   @Test
   public void unaryPrefix() {
      inner.arr[1]++;
      assertTrue(inner.arr[2] == 3);
      this.inner.arr[2]++;
      assertTrue(inner.arr[2] == 4);
   }

   public void intMethod(int foo, int expected) {
      assertTrue(foo == expected);
   }
}
