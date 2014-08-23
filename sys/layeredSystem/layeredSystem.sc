// This layer does not have any code now but acts as a placeholder for all layers which depend on the LayeredSystem, dynamic model runtime, etc.
// When you extend this layer, it's a way to say that your code needs to run on the server because it uses these apis.  Since these apis are already available for all server
// code there's not much else to do in this layer but exclude the javascript runtime.
sys.layeredSystem {
   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.Program;

   hidden = true;
   compiledOnly = true;

   void initialize() {
      // Exclude the runtimes which do not support the LayeredSystem dynamic features.  
      // All layers which extend this layer explicitly will also be excluded, unless they explicitly include a layer which uses JS
      excludeRuntimes("js", "android", "gwt");

      // The servlet stuff requires the default runtime
      addRuntime(null);
   }
}
