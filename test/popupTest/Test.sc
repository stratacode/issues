@MainInit
object Test extends AppFrame {
   title = "Test";

   object popupMenu extends JPopupMenu implements ActionListener {
      object menuItem1 extends JMenuItem {
	 text = "One";
	 { addActionListener(popupMenu); }
      }
      object menuItem2 extends JMenuItem {
	 text = "Two";
	 { addActionListener(popupMenu); }
      }
      /*
      { add(menuItem1);
	add(menuItem2);
      }
      */
      void actionPerformed(ActionEvent event) {
         JMenuItem menuItem = (JMenuItem) event.source;
         if (menuItem == menuItem1)
            System.out.println("+++++ pressed One");
         else if (menuItem == menuItem2) 
            System.out.println("+++++ pressed Two");
      }
   }
   
   object popupListener extends MouseAdapter {
      public void mousePressed(MouseEvent event) {
	 showPopupMenu(event);
      }
      public void mouseReleased(MouseEvent event) {
	 showPopupMenu(event);
      }
      void showPopupMenu(MouseEvent event) {
	 if (event.isPopupTrigger())
	    popupMenu.show(event.getComponent(), event.getX(), event.getY());
      }
   }

   object textArea extends JTextArea {
      size = SwingUtil.dimension(windowWidth, windowHeight);
      editable = false;
      { addMouseListener(popupListener); }
   }
}

