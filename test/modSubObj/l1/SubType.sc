class SubType extends Type {
   innerType1 {
      a = 11;
      b = 22;

      innerInner1 {
         aa = 111;
         bb = 222;
      }
   }

   @sc.obj.MainSettings
   public static void main(String[] argv) {
      SubType t = new SubType();
      System.out.println("11 = " + t.innerType1.a);
      System.out.println("22 = " + t.innerType1.b);
      System.out.println("111 = " + t.innerType1.innerInner1.aa);
      System.out.println("222 = " + t.innerType1.innerInner1.bb);
   }
}
