StaticInherit {
   public String foo() {
      String res = super.foo() + " overridden";
      System.out.println(res);
      assertTrue(res.equals("foo base overridden"));
      return res;
   }

   @Test public void bar() {
      foo();
   }

}
