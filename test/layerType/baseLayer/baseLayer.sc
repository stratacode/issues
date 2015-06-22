test.layerType.baseLayer {
   codeType = CodeType.Framework;
   codeFunction = CodeFunction.Program;
   
   int layerField = 222;

   object subObject {
      int subField1 = 1;
   }

   object testFileProc extends LayerFileProcessor {
      prependLayerPackage = false;
      useSrcDir = false;
      processInAllLayers = true;
      srcPathTypes = {null, "web"};
      extensions = {"html"};
   }

   void initialize() {
      System.out.println("layerField from baseLayer.init - 444 = " + layerField);
      System.out.println("subField from baseLayer.init - 3 = " + subObject.subField1);
   }

   void start() {
      System.out.println("layerField from baseLayer.start - 444 = " + layerField);
      System.out.println("subField from baseLayer.start - 3 = " + subObject.subField1);
   }
}
