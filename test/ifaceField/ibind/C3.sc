public class C3 implements I6 {
  f0 = 10;
  f1 = 11;

  int res := f0 + f1 + f2 + f3 + f4;

  @Test
  void fieldsInited() {
    assertTrue(f0 == 10);
    assertTrue(f1 == 11);
    assertTrue(f2 == 622);
    assertTrue(f3 == 3);
    assertTrue(f4 == 64);
    assertTrue(res == 710);
  }
}
