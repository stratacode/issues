ModComp2 {
   foo = 10;
   bar = 20;

   public void method1() {
      System.out.println("*** before super foo=" + foo + " bar=" + bar);
      super.method1();
      System.out.println("*** after super foo=" + foo + " bar=" + bar);
   }
}
