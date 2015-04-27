class underLiteral {

   long creditCardNumber = 1234_5678_9012_3456L;
   long socialSecurityNumber = 999_99_9999L;
   float pi =      3.14_15F;
   long hexBytes = 0xFF_EC_DE_5E;
   long hexWords = 0xCAFE_BABE;
   long maxLong = 0x7fff_ffff_ffff_ffffL;
   byte nybbles = 0b0010_0101;
   long bytes = 0b11010010_01101001_10010100_10010010;

   @Test
   public void runTest() {
      assertEquals(creditCardNumber, 1234567890123456L);
      assertEquals(socialSecurityNumber, 999999999L);
      assertEquals(pi, 3.1415F, 0.0);
      assertEquals(hexBytes, 0xFFECDE5E);
      assertEquals(hexWords, 0xCAFEBABE);
      assertEquals(maxLong, 0x7fffffffffffffffL);
      assertEquals(nybbles, 0b00100101);
      assertEquals(nybbles, 0x25);
      assertEquals(bytes, 0xd2699492);

   }

}
