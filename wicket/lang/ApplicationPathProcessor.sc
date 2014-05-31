public class ApplicationPathProcessor extends sc.lang.DefaultAnnotationProcessor {
  // This gets called when a new dynamic component is added with the ApplicationPath
  // annotation.  Eventually we can use this hook to register new Applications dynamically
  // without restarting?
  protected void typeGroupMemberStarted(sc.lang.java.TypeDeclaration td) {
  }
}
