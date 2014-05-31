ModComp3 {
   field1 = 10;
   field2 = 20;
   field3 = 30;
   field4 = 40;

   public int method1() {
      System.out.println("*** before super field1=" + field1 + " field2=" + field2);
      super.method1();
      System.out.println("*** after super field3=" + field3 + " field4=" + field4);
      return field1;
   }
}
