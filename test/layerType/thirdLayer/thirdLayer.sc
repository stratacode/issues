test.layerType.thirdLayer extends subLayer {
   layerField = 444;

   subObject {
      subField1 = 3;
   }

   void init() {
      System.out.println("layerField from thirdLayer - 444 = " + layerField);
      System.out.println("subField from thirdeLayer.init - 3 " + subObject.subField1);
   }
}
