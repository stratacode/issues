sys.std {
   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.Program;

   hidden = true;
   compiledOnly = true;

   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();
      sc.layer.LayerFileProcessor configFileProcessor = new sc.layer.LayerFileProcessor();

      // Only layers after this one will see this extension
      configFileProcessor.definedInLayer = this;    
      configFileProcessor.prependLayerPackage = false;
      configFileProcessor.useSrcDir = false;

      // Copy these extensions to the output file
      system.registerFileProcessor("xml", configFileProcessor, this);
      system.registerFileProcessor("properties", configFileProcessor, this);
      system.registerFileProcessor("css", configFileProcessor, this);

      // Need to add extensions of any files produced so that we know where they are in the build src index - src dir (e.g. java or js), output dir - e.g. build or build/web).
      system.registerFileProcessor("sh", configFileProcessor, this);

      sc.lang.TemplateLanguage templateResourceLang = new sc.lang.TemplateLanguage();
      templateResourceLang.processTemplate = true;
      templateResourceLang.definedInLayer = this;
      templateResourceLang.buildPhase = sc.layer.BuildPhase.Process;
      templateResourceLang.resultSuffix = "xml";
      templateResourceLang.useSrcDir = false;
      templateResourceLang.prependLayerPackage = false;

      registerLanguage(templateResourceLang, "scxml");

      sc.lang.TemplateLanguage scshLang = new sc.lang.TemplateLanguage();
      scshLang.processTemplate = true;
      scshLang.definedInLayer = this;
      scshLang.buildPhase = sc.layer.BuildPhase.Process;
      scshLang.resultSuffix = "sh";
      scshLang.useSrcDir = false;
      scshLang.prependLayerPackage = false;
      scshLang.makeExecutable = true;

      registerLanguage(scshLang, "scsh");
   }
}
