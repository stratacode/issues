test.layerType.subLayer extends baseLayer {
   layerField = 333;

   subObject {
      subField1 = 2;
   }

   void initialize() {
      System.out.println("layerField from subLayer - 444 = " + layerField);
      System.out.println("subField from subLayer.init - 3 = " + subObject.subField1);
   }
}
