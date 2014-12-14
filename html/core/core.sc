package sc.html;

import sc.html.URL;
import sc.html.MainInit;

import sc.lang.html.Element;
import sc.lang.html.MouseEvent;
import sc.lang.html.Window;
import sc.lang.html.Document;

/** 
 * This layer is the core of the HTML template package, shared by the servlet.schtml and js.schtml
 * layers.
 */
html.core extends sys.std {  // Extending sys.std because we override the standard file type handling.  Need to then have us after sys.std.
   compiledOnly = true;

   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.Program;

   public void initialize() {
      sc.layer.LayeredSystem system = getLayeredSystem();
     
      // Languages are defined in the initialization stage so that they are available to help process
      // files in the IDE.  The IDE will initialize the layer when source is loaded but won't start it
      // until you run the application.
      sc.lang.TemplateLanguage templateResourceLang = new sc.lang.TemplateLanguage();
      templateResourceLang.processTemplate = true;
      //templateResourceLang.definedInLayer = this;
      // For gwt, xml files are used by the compiler itself so they need to be done
      // before the process phase in prepare.  May need to register a separate xml pattern for the specific build files
      // if we need to scxml elsewhere with different rules (i.e. that go into the web root)
      templateResourceLang.buildPhase = sc.layer.BuildPhase.Prepare;
      templateResourceLang.resultSuffix = "xml";
      templateResourceLang.useSrcDir = false;
      templateResourceLang.prependLayerPackage = false;
      // Share one buildDir for web root files since they do not support path searching
      //templateResourceLang.useCommonBuildDir = true;
      registerLanguage(templateResourceLang, "scxml");

      // We use a singleton here for the IDE in particular so we can match parselets by identity
      templateResourceLang = sc.lang.HTMLLanguage.getHTMLLanguage();

      // Generating both an .html and a .java file from this type.  The html file is generated during the postBuild
      // phase - aftr the system has been built and those classes are loaded.
      templateResourceLang.compiledTemplate = true;
      templateResourceLang.postBuildTemplate = true;

      templateResourceLang.prependLayerPackage = true;
      // As a type we need the package but for saving the result file we do not (when compiledTemplate=true and processTemplate=true)
      templateResourceLang.prependLayerPackageOnProcess = false;

      //templateResourceLang.definedInLayer = this;
      templateResourceLang.buildPhase = sc.layer.BuildPhase.Process;
      templateResourceLang.resultSuffix = "html";
      templateResourceLang.useSrcDir = false;

      // Share one buildDir for web root files since they do not support path searching
      templateResourceLang.useCommonBuildDir = true;
      // To support more natural "code behind", schtml will do a modify by default of the previous file
      templateResourceLang.defaultModify = true;
      // for a file composed of for example just <html>...</html>, the type of the file is taken from the root element, not made a child of a "Page" class
      templateResourceLang.compressSingleElementTemplates = true;
      templateResourceLang.defaultExtendsType = "sc.lang.html.Page";
      // Do not try to generate .html files for types which do not have <html> or otherwise indicate they are addressed by a URL or have MainInit set on them.
      templateResourceLang.processOnlyURLs = true;
      // The .sc and .schtml files replace each other in the type system - i.e. not part of the "processed id" which lets one file in a subsequent layer from processing that file in the next layer
      // the suffix will be .java when compiledTemplate is true and one of process or postBuild template
      //templateResourceLang.processByUniqueSuffix = true;
      registerLanguage(templateResourceLang, "schtml");

      sc.lang.TemplateLanguage cssLanguage = new sc.lang.CSSLanguage();

      // For now generating the class for these types like schtml; TODO write a CSSLanguage with its' owner parser for better expressions and CSS syntax for layering
      cssLanguage.compiledTemplate = true;
      cssLanguage.postBuildTemplate = true;

      // For a simpler implementation, when your css files are static for the site, just process them at compile time.  Won't allow access to runtime 
      // state but for this use case you don't need that.
      //cssLanguage.processTemplate = true;

      // do generate the java class with the layer's package name
      cssLanguage.prependLayerPackage = true;

      // But not the file generated in the process phase
      cssLanguage.prependLayerPackageOnProcess = false;

      cssLanguage.resultSuffix = "css";
      // Use processPrefix here so that the processedName is computed as a regular java type
      // name so we can replace scss files with sc files.
      cssLanguage.processPrefix = "web";

      // Only layers after this one will see this extension
      //cssLanguage.definedInLayer = this;  
      registerLanguage(cssLanguage, "scss");

      // As a type we need the package but for saving the result file we do not (when compiledTemplate=true and processTemplate=true)
      cssLanguage.prependLayerPackageOnProcess = false;
      cssLanguage.useSrcDir = false;

      // Share one buildDir for web root files since they do not support path searching
      cssLanguage.useCommonBuildDir = true;
      // To support more natural "code behind", schtml will do a modify by default of the previous file
      cssLanguage.defaultModify = true;

      cssLanguage.defaultExtendsType = "sc.lang.css.StyleSheet";
   }

   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();

      // Add this here in core even though it's used in schtml so that it's always in front of js.core where the sc.tag package is added.
      system.addTagPackageDirectory("sc.html.tag", this, 1);

      // Layers web files in the "doc" folder of any downstream layers
      sc.layer.LayerFileProcessor webFileProcessor = new sc.layer.LayerFileProcessor();

      // We do not use the layer's package in computing the web file names  An alternate design is to turn this off and pick
      // a fixed doc root.  This design ties paths in the doc root to types in Java.  I like this
      // design but where we can map the web root to some package in the hierarchy.
      webFileProcessor.prependLayerPackage = false;
      // Should we prepend the 'web' prefix onto .css and other files so they can live in the top-level layer folder?
      // Right now, this prefix gets added in the servlet.schtml, js.schtml and wicket.core libraries.
      // GWT requires a separate doc root which got messed up by this scheme so it is not set here.
      //webFileProcessor.templatePrefix = "web";
      webFileProcessor.useSrcDir = false;
      // Since jetty does not support path searching, all web files go into the one common buildDir
      // and that is where jetty starts.
      //webFileProcessor.useCommonBuildDir = true;
      webFileProcessor.processInAllLayers = true;

      // Copy these extensions to the output file - TODO: restrict these to files only in the "web" subdir?
      registerFileProcessor(webFileProcessor, "html");
      registerFileProcessor(webFileProcessor, "jpg");
      registerFileProcessor(webFileProcessor, "png");
      registerFileProcessor(webFileProcessor, "gif");
      registerFileProcessor(webFileProcessor, "pdf");
      registerFileProcessor(webFileProcessor, "css");
      registerFileProcessor(webFileProcessor, "js");
      registerFileProcessor(webFileProcessor, "xml");
      registerFileProcessor(webFileProcessor, "properties");

      String openSuffix = system.options.openPattern;
      if (openSuffix == null)
         openSuffix = system.serverEnabled ? "" : "index.html";;

      String webURL = system.getServerURL() + openSuffix;

      // Open up to the index page for this layer or use the -o URL option to open a specific page.
      if (system.options.openPageAtStartup)
         system.addRunCommand("open",  webURL);

      // Add a shell script to use with the -t option
      system.addTestCommand("./runTest.sh");

      // Warn the user if there's no upstream layer for either javascript or a server implementation
      if (activated && system.getLayerByDirName("js.template") == null && system.getLayerByDirName("servlet.schtml") == null && system.getLayerByDirName("wicket.core") == null && system.getLayerByDirName("html.schtml") == null && system.getLayerByDirName("js.schtml") == null) {
         System.err.println("*** Warning: html.core layer included without html.schtml, js.schtml and servlet.schtml.  Include js.schtml for a client, servlet.schtml for a server or both for a synchronized implementation. By itself, html.core provides an API for HTML generation but does not even generate a default HTML file.");
      }

      sc.lang.DefaultAnnotationProcessor urlProc = new sc.lang.DefaultAnnotationProcessor();
      // Need to add a static code snippet to register the page.  If we happen to register an inner class the addPage still goes on the parent type
      urlProc.validOnField = false;
      urlProc.validOnClass = true;
      urlProc.validOnObject = true;
      urlProc.initOnStartup = true;
      urlProc.typeGroupName = "URLTypes";
      urlProc.inherited = true; // Include any sub-type which has URL in the type group
      urlProc.skipAbstract = true; // Don't include any abstract macro templates
      registerAnnotationProcessor("sc.html.URL", urlProc);

      sc.lang.DefaultAnnotationProcessor mainInitProc = new sc.lang.DefaultAnnotationProcessor();
      mainInitProc.typeGroupName = "mainInit";
      mainInitProc.validOnField = false;
      mainInitProc.validOnObject = true;
      mainInitProc.inherited = true; // Include any sub-type which has MainInit
      mainInitProc.skipAbstract = true; // Don't include any abstract macro templates
      mainInitProc.subTypesOnly = true; // Don't include the Html and Page types which set the annotation
      registerAnnotationProcessor("sc.html.MainInit", mainInitProc);

      if (activated) {
         // When either of these type groups change, we need to regenerate the runTest script
         system.addTypeGroupDependency("runTest.scsh", "runTest", "mainInit");
         system.addTypeGroupDependency("runTest.scsh", "runTest", "urlTypes");
      }
   }

}
