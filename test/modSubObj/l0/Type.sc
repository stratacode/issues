class Type {
   object innerType1 {
      int a = 1;
      int b = 2;

      object innerInner1 {
         int aa = 10;
         int bb = 20;
      }
   }

   @sc.obj.MainSettings
   public static void main(String[] argv) {
      Type t = new Type();
      System.out.println("1 = " + t.innerType1.a);
      System.out.println("2 = " + t.innerType1.b);
      System.out.println("10 = " + t.innerType1.innerInner1.aa);
      System.out.println("20 = " + t.innerType1.innerInner1.bb);
   }
}
