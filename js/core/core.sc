package sc.js;

public js.core extends html.core, html.schtml, js.prebuild, js.sys, js.util, sys.sccore {
   compiledOnly = true;

   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.Program;
  
   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();

      if (system.runtimeProcessor instanceof sc.lang.js.JSRuntimeProcessor)
          ((sc.lang.js.JSRuntimeProcessor) system.runtimeProcessor).destinationName = "jsHttp";

      //system.tagPackageList.add(0, "sc.tag");
      sc.lang.TemplateLanguage tempLang = (sc.lang.TemplateLanguage) system.getFileProcessorForExtension("schtml");
      tempLang.processPrefix = "web";

      // This can either be the web file processor or the one from util
      sc.layer.LayerFileProcessor webProc = (sc.layer.LayerFileProcessor) system.getFileProcessorForExtension("css");
      webProc.templatePrefix = "web";

      // This will always be the web file processor
      webProc = (sc.layer.LayerFileProcessor) system.getFileProcessorForExtension("html");
      webProc.templatePrefix = "web";
   }
}
