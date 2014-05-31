/** A placeholder so there's nice symmetric: html, js, servlet, jetty, etc. 
    Should we separate the schtml functionality so it's not present in html.core? */
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

   }
}
