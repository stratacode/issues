class ObjectNC {

   class BaseType {
      int a, b;
   }

   object oType extends BaseType {
   }

   int foo = oType.a;
   int bar := oType.b;
}
