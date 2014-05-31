class C1 {
  int a = 1;
  int b = 2;

  static int sa = 1;
  static int sb = 2;

  int c = (int) (a * 3.0 + 5/b);
  double d = a * b / 3.0;
  static double sd = sa * sb / 3.0;

  int e = (int) d;
  static int es = (int) sd;

  static int static_c = 3;

  void foo() {
     a = b * 2;
     static_c += 1;
     int[] iarray = new int[10];
     for (int i = 0; i < iarray.length; i++) {
        iarray[i] = i;
     }

     for (int i = 0; i < iarray.length; i++) {
        if (iarray[i] != i)
           throw new IllegalArgumentException("test failed: " + i);
     }

     System.out.println("method foo - array init test passed");
  }

  void bar() {
     int iarray[] = new int[10];
     for (int i = 0; i < iarray.length; i++) {
        iarray[i] = i;
     }

     for (int i = 0; i < iarray.length; i++) {
        if (iarray[i] != i)
           throw new IllegalArgumentException("test failed: " + i);
     }

     System.out.println("method bar - array init with old-school array declaration test passed");
  }
}
