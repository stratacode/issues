class EditorPanel {

   void init() {
      System.out.println("EditorPanel init: " + this);
      System.out.println("EditorPanel init: " + toString());
      System.out.println("=" + "super:EditorFrame this: page:page:body:editorMixin:EditorPanel");
      assertTrue(toString().equals("super:EditorFrame this: page:page:body:editorMixin:EditorPanel"));
   }

}
