public class C3 implements I6 {
  f0 = 10;
  f1 = 11;

  @Test
  void fieldsInited() {
    assertTrue(f0 == 10);
    assertTrue(f1 == 11);
    assertTrue(f2 == 622);
    assertTrue(f3 == 3);
    assertTrue(f4 == 64);
  }
}
