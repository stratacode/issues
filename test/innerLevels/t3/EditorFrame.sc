class EditorFrame {
   class editorPanel extends EditorPanel {

      String toString() {
         return EditorFrame.this + ":" + "EditorPanel";
      }
   }

   String toString() {
      return "EditorFrame";
   }

   void init() {
      editorPanel panel = new editorPanel();
      panel.init();
   }
}
