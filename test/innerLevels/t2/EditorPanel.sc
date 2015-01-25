class EditorPanel {

   String getString() {
      return "EditorPanel";
   }

   void init() {
      String res = this.getString();
      System.out.println("EditorPanel init: " + res);
      System.out.println("=" + "super:EditorFrame this: page:page:body:editorMixin:EditorPanel");
      assertTrue(getString().equals("super:EditorFrame this: page:page:body:editorMixin:EditorPanel"));
   }

}
