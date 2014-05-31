public class AddressForm extends JFrame {
   public int xpad = 5, ypad = 5;
   public static int gap = 10;
   public static int baseline = 5;

   visible = true;
   defaultCloseOperation = JFrame.EXIT_ON_CLOSE;
   size = SwingUtil.dimension(300, 300);

   public object addressPanel extends JPanel {
      size := AddressForm.this.size;

      object address1Label extends JLabel {
         text = "Address 1";
         labelFor = address1Field;
         //location := SwingUtil.point(xpad, ypad + baseline);
         location := new Point(xpad, ypad + baseline);
         size := preferredSize;
      }
      object address1Field extends JTextField {
         location := SwingUtil.point(xpad + ((int) address1Label.preferredSize.width) + gap, ypad);
         size := SwingUtil.dimension((int)addressPanel.size.width - (int)address1Label.size.width - xpad - 2*gap, (int)preferredSize.height);;
      }

      int row2y := address1Field.height + 2*ypad;
      object address2Label extends JLabel {
         text = "Address 2";
         labelFor = address2Field;
         size = preferredSize;
         location := SwingUtil.point((int)address1Label.location.x, row2y + baseline);
      }
      object address2Field extends JTextField {
         //columns = 14;
         location := SwingUtil.point(xpad + ((int) address2Label.preferredSize.width) + gap, row2y);
         //size = preferredSize;
         size := SwingUtil.dimension((int)addressPanel.size.width - (int)address2Label.size.width - xpad - 2*gap, (int)preferredSize.height);
      }

      int row3y := row2y + address2Field.height + ypad;
      object cityLabel extends JLabel {
         text = "City";
         labelFor = cityField;
         location := SwingUtil.point((int)address1Label.location.x, row3y + baseline);
         size := preferredSize;
      }
      public object cityField extends JTextField {
         text :=: stateField.text;
         location := SwingUtil.point(xpad + ((int) cityLabel.preferredSize.width) + gap, row3y);
         size := SwingUtil.dimension((int)addressPanel.size.width - (int)stateLabel.size.width - (int)stateField.size.width - (int)cityLabel.size.width - gap*4 - xpad, (int)preferredSize.height);
      }

      object stateLabel extends JLabel {
         text = "State";
         labelFor = stateField;
         location := SwingUtil.point((int)cityField.location.x + (int)cityField.size.width + gap, row3y + baseline);
         size = preferredSize;
      }
      public object stateField extends JTextField {
         columns = 4;
         location := SwingUtil.point((int)stateLabel.location.x + ((int) stateLabel.preferredSize.width) + gap, row3y);
         size = preferredSize;
      }
   }
}
