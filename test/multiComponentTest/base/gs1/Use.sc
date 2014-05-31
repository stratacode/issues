package com.vs.gs1;

object Use extends Base {
   foo = 33;
   int baz = foo;

   bar = new Sub();

   int zz = bar.subf1;

   {
      bar.subf1 = 123;
      bar.subp1 = 456;

      bar.subSub.ss1 = 333;
      bar.subSub.ss2 = 444;
      bar.subSub.SS3 = 555;
   }

}
