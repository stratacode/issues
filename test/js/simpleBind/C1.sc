class C1 {
  int a = 1;
  int b := a * 2;
  int c :=: b;

  static int sa = 1;
  static int sb := sa * 2;
  static int sc :=: sb;

  void foo() {
    sc.js.bind.Bind.printAllBindings();
  }
}
