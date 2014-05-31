// No package prefix here because we put the "gen" directory in this layer.  It's defined in terms of root

/** 
 * This layer contains basic android support.  It defines the various build options to generate an android project in the sc buildDir.
 * Extends util because it overrides some of the util functionality for scxml.
 *
 * To install: 
 * Install the android sdk in /usr/local/androidsdk
 * verify the link: layers/android/lib link to platform dir you want to use, typically: /usr/local/androidsdk/platforms/android-15
 * verify the link: layers/android/sdk link to platform dir you want to use, typically: /usr/local/androidsdk
 */
android.lib extends util {
   inheritPackage = false;
   // This gets inherited from any sub-layers and tells the system to put the src into a sub-directory of the normal build directory
   buildSrcSubDir = "src";
 
   {
      layeredSystem.runtimePrefix = "";
      excludedPaths.add("sdk");
      excludedFiles.add(".*.d");
   }

   // Include Android's generated source - R.java in the layer's src path
   srcPath = "${buildDir}/gen:.";

   needsIndexRefresh = true; // We add files to the "gen" directory in the pre-build commands.  This tells the layer to flush the cache after each phase

   // Directory holding this file has a symbolic link to the android/platforms/android-xxx directory which contains android.jar
   public void start() {
      String platformDir = System.getenv("ANDROID_SDK");
      if (platformDir == null)
         platformDir = getRelativeFile("lib");

      // Include android.jar into the classpath
      classPath = sc.util.FileUtil.concat(platformDir, "android.jar");

      if (!(new java.io.File(platformDir).isDirectory())) {
         // TODO: why not prompt to fix it here!
         throw new IllegalArgumentException("*** Need a link from: " + platformDir + " to the android platform directory (e.g. /usr/local/androidsdk/platform-15s/)");
      }
      else if (!(new java.io.File(classPath).canRead())) {
         throw new IllegalArgumentException("*** Android SDK is missing a readable android.jar at: " + classPath);
      }

      String sdkDir = getRelativeFile("sdk");
      if (!(new java.io.File(sdkDir).isDirectory())) {
         throw new IllegalArgumentException("*** Need a link from: " + sdkDir + " to the android sdk directory (e.g. /usr/local/androidsdk/)");
      }

      sc.layer.LayeredSystem system = getLayeredSystem();

      // Turn off compilation since we need to use android to do this. 
      system.options.noCompile = true;

      // Copies scrt.jar into the android libs directory
      system.runtimeLibsDir = sc.util.FileUtil.concat(system.buildDir, "libs");
      
      // Recognizes android resources - copies them over to the build directory using a layered merging of paths
      sc.layer.LayerFileProcessor resourceFileProcessor = new sc.layer.LayerFileProcessor();

      // Only layers after this one will see this extension
      resourceFileProcessor.definedInLayer = this;    
      resourceFileProcessor.prependLayerPackage = false;
      resourceFileProcessor.useSrcDir = false;
      // Build this before we process Java files
      resourceFileProcessor.buildPhase = sc.layer.BuildPhase.Prepare;

      // Copy these extensions to the output file
      system.registerFileProcessor("xml", resourceFileProcessor, this);
      system.registerFileProcessor("properties", resourceFileProcessor, this);
      system.registerFileProcessor("png", resourceFileProcessor, this);

      sc.lang.TemplateLanguage tempLang = new sc.lang.TemplateLanguage();
      tempLang.processTemplate = true;
      tempLang.definedInLayer = this;
      // For android, xml files are used by the compiler itself so they need to be done
      // before the process phase in prepare.
      tempLang.buildPhase = sc.layer.BuildPhase.Prepare;
      tempLang.resultSuffix = "xml";
      tempLang.useSrcDir = false;
      sc.parser.Language.registerLanguage(tempLang, "scxml");

      // First make the gen folder if necessary
      system.addPreBuildCommand(sc.layer.BuildPhase.Prepare, this, "mkdir", "-p", sc.util.FileUtil.concat(system.buildDir, "gen"), sc.util.FileUtil.concat(system.buildDir, "res"));
      // Then run the aapt command which requires it - needs a "tools" symbolic link to this directory in the sdk
      system.addPreBuildCommand(sc.layer.BuildPhase.Process, this, sc.util.FileUtil.concat(platformDir,"tools/aapt"),
                                "package", "-m", "-J", "gen", "-M", "AndroidManifest.xml", "-S", "res", "-I", classPath);

      // Change debug to release for the production install or just comment this out and run ant by hand in the build directory
      system.addPostBuildCommand(sc.layer.BuildPhase.Process, this, "ant", "debug", "install");
   }
}
