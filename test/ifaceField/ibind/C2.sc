public class C2 implements I3 {
  f0 = 10;
  f1 = 11;
  int f3 = 33;
  int f4 = 4;

  int res := f0 + f1 + f2 + f3 + f4;

  @Test
  void fieldsInited() {
    assertTrue(f0 == 10);
    assertTrue(f1 == 11);
    assertTrue(f2 == 2);
    assertTrue(f3 == 33);
    assertTrue(f4 == 4);
    assertTrue(res == 60);
  }
}
