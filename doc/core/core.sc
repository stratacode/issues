package sc.doc;

// Should this be necessary?   Currently we do not prepend the layer's package to the lookup for relative types
// when prependLayerPackage is false (i.e. for the vdoc and web.xml type files).  They will have to explicitly
// import stuff even when it is in the same directory.
import sc.doc.templateSnippets;

//doc extends example.unitConverter.adjust, jetty.examp, example.extendedHelloWorld, example.extendedHelloWorld2, js.schtml {
doc.core extends servlet.options.globalScope, doc.tag, jetty.schtml, js.schtml {
   {
      // Add documentation format
      sc.lang.TemplateLanguage docLang = new sc.lang.TemplateLanguage();
      docLang.processTemplate = true;
      docLang.prependLayerPackage = false;
      docLang.evalToString = true; // Support <%= templateName %> - template will implement toString by evaluating itself
      docLang.resultSuffix = "html";
      docLang.templatePrefix = "web";

      // Write output to the layer directory
      //docLang.outputDir = layerPathName;

      // This command is run after the template is evaluated
      //docLang.filterCommand = java.util.Arrays.asList(getRelativeFile("bin/markdown")); 
      docLang.defaultExtendsType = "sc.doc.MarkDownPage";

      // Only layers after this one will see this extension
      docLang.definedInLayer = this;  
      sc.parser.Language.registerLanguage(docLang, "vdoc");

      sc.layer.LayeredSystem sys = getLayeredSystem();

      // Don't want our output files to be treated as input files after the build!
      //sys.removeFileProcessor("html", this);

      System.setProperty("markDown.cmd", getRelativeFile("bin/markdown"));
   }

   public void start() {
      // For now we are specifying site-wide sections of head etc. using the default tag class.  Alternatively we would add an extends to each page if we need
      // to customize this further.
      //layeredSystem.tagPackageList.add(0, "sc.doc.tag");
 
      // Don't want to compile the schtml files we use as sample source
      //sc.parser.Language.removeLanguage("schtml");
   }
}
