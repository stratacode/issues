import sc.bind.Bind;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class AddressFormStandalone extends JFrame {
   public int xpad := addressPanel.xpadSlider.value, ypad := addressPanel.ypadSlider.value;
   public int gap := addressPanel.gapSlider.value;
   public int baseline := addressPanel.baselineSlider.value;

   visible = true;
   {
      setSize(300, 300);
   }

   public object addressPanel extends JPanel {
      size := AddressFormStandalone.this.size;

      object address1Label extends JLabel {
         text = "Address 1";
         labelFor = address1Field;
         location := SwingUtil.point(xpad, ypad + baseline);
         size := preferredSize;
      }
      object address1Field extends JTextField {
         location := SwingUtil.point(xpad + ((int) address1Label.preferredSize.width) + gap, ypad);
         size := SwingUtil.dimension((int)addressPanel.size.width - (int)address1Label.size.width - xpad - 2*gap, (int)preferredSize.height);;
      }

      object address2Label extends JLabel {
         text = "Address 2";
         labelFor = address2Field;
         size = preferredSize;
         location := SwingUtil.point((int)address1Label.location.x, address1Field.y + address1Field.height + ypad + baseline);
      }
      object address2Field extends JTextField {
         //columns = 14;
         location := SwingUtil.point(xpad + ((int) address2Label.preferredSize.width) + gap, address1Field.height + ypad);
         //size = preferredSize;
         size := SwingUtil.dimension((int)addressPanel.size.width - (int)address2Label.size.width - xpad - 2*gap, (int)preferredSize.height);
      }
      object cityLabel extends JLabel {
         text = "City";
         labelFor = cityField;
         location := SwingUtil.point((int)address1Label.location.x, address2Field.y + address2Field.height + ypad + baseline);
         size := preferredSize;
      }
      public object cityField extends JTextField {
         text :=: stateField.text;
         location := SwingUtil.point(xpad + ((int) cityLabel.preferredSize.width) + gap, address2Field.y + address2Field.height + ypad);
         size := SwingUtil.dimension((int)addressPanel.size.width - (int)stateLabel.size.width - (int)stateField.size.width - (int)cityLabel.size.width - gap*4 - xpad, (int)preferredSize.height);
      }

      object stateLabel extends JLabel {
         text = "State";
         labelFor = stateField;
         location := SwingUtil.point((int)cityField.location.x + (int)cityField.size.width + gap, (int)cityLabel.location.y);
         size = preferredSize;
      }
      public object stateField extends JTextField {
         columns = 4;
         location := SwingUtil.point((int)stateLabel.location.x + ((int) stateLabel.preferredSize.width) + gap, (int)cityField.location.y);
         size = preferredSize;
      }

      int sliderWidth := (int)size.width/4;
      int sliderY := (int) stateField.location.y + (int)stateField.size.height + ypad;
      int sliderHeight = 30;

      object xpadSlider extends JSlider {
         minimum = 0;
         maximum = 20;
         orientation = HORIZONTAL;
         value = 10;

         location := SwingUtil.point(address1Label.x, sliderY);
         size := SwingUtil.dimension(sliderWidth, sliderHeight);
      }

      object ypadSlider extends JSlider {
         minimum = 0;
         maximum = 20;
         orientation = HORIZONTAL;
         value = 10;

         location := SwingUtil.point(address1Label.x + sliderWidth, sliderY);
         size := SwingUtil.dimension(sliderWidth, sliderHeight);
      }

      object baselineSlider extends JSlider {
         minimum = 0;
         maximum = 20;
         orientation = HORIZONTAL;
         value = 5;

         location := SwingUtil.point(address1Label.x + 2*sliderWidth, sliderY);
         size := SwingUtil.dimension(sliderWidth, sliderHeight);
      }

      object gapSlider extends JSlider {
         minimum = 0;
         maximum = 20;
         orientation = HORIZONTAL;
         value = 10;

         location := SwingUtil.point(address1Label.x + 3*sliderWidth, sliderY);
         size := SwingUtil.dimension(sliderWidth, sliderHeight);
      }
   }

   defaultCloseOperation = JFrame.EXIT_ON_CLOSE;
}
