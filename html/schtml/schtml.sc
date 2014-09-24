/** 
   html.schtml defines default tag objects for the sc.html.tag package that provide nice defaults for
   schtml applications - e.g. session scope pages, load the JS after the body etc.  Most apps will extend this
   layer unless they require special JS semantics like the js.allInOne mode - which runs the entire app like a java 'main"
   loaded into the index page.  
   */
html.schtml extends html.core {
   exportPackage = false;
   compiledOnly = true;

   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.Program;

   void start() { 
      sc.layer.LayeredSystem system = getLayeredSystem();
      sc.lang.TemplateLanguage tempLang = (sc.lang.TemplateLanguage) system.getFileProcessorForExtension("schtml");
      tempLang.processPrefix = "web";

      // Note: this sets the templatePrefix for all web files since there's one processor used for all of them.
      sc.layer.LayerFileProcessor webProc = (sc.layer.LayerFileProcessor) system.getFileProcessorForExtension("css");
      webProc.templatePrefix = "web";

      // We modify the tag library and so need to add a dependency on html.core
      addModifiedLayer("html.core");
   }
}
