class EditorFrame {
   class editorPanel extends EditorPanel {

      String getString() {
         return EditorFrame.this.getString() + ":" + "EditorPanel";
      }
   }

   String getString() {
      return "EditorFrame";
   }

   void init() {
      editorPanel panel = new editorPanel();
      panel.init();
   }
}
