import sc.layer.LayeredSystem;
import sc.layer.BuildInfo;
import sc.lang.DefaultAnnotationProcessor;
import sc.lang.ILanguageModel;
import sc.lang.java.Definition;
import sc.lang.java.MethodDefinition;
import sc.lang.java.Annotation;
import sc.lang.java.ModelUtil;

public class DebugAnnotationProcessor extends DefaultAnnotationProcessor {
   public void process(Definition def, Annotation annot) {
      if (!(def instanceof MethodDefinition) || !(ModelUtil.hasModifier(def, "public") || ModelUtil.hasModifier(def, "static"))) {
         def.displayError("Test annotation should only be attached to public instance methods: ");
      }
      else {
         LayeredSystem sys = def.getLayeredSystem();

         String enclosingTypeName = ModelUtil.getTypeName(def.getEnclosingType());
         BuildInfo bi = sys.buildInfo;

         if (bi.getTestInstance(enclosingTypeName) == null)
            bi.addTestInstance(new BuildInfo.TestInstance(enclosingTypeName, "junit"));
      }
   }
}
