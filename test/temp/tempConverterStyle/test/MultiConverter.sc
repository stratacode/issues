MultiConverter {
   foreground := Color.WHITE;
   background := Color.BLACK;
   errorLabel {
     foreground = Color.RED;
   }
   unit1Label {
     foreground := MultiConverter.this.foreground;
   }
   unit2Label {
     foreground := MultiConverter.this.foreground;
   }
}
