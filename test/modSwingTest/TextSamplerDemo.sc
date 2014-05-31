TextSamplerDemo {
   textPane {
      initString = {"an", "overridden", "string"};
   }

   textFieldLabel {
      text = "eek";
   }

   splitPane {
      leftComponent = textScrollPane;
      rightComponent = editorScrollPane;
   }


   object rightPane extends JPanel {
      layout = new GridLayout(1,0);
      border = BorderFactory.createCompoundBorder(
           BorderFactory.createTitledBorder("Overridden Panel"),
           BorderFactory.createEmptyBorder(2,2,2,2));
      rightPane() {
         add(splitPane); 
      } 
   }
}
