public class MultiConverterT extends JFrame {
   public int xpad = 5, ypad = 5;
   public int gap = 10;
   public int baseline = 5;
   public int windowWidth := (int)size.width;
   public int windowHeight := (int)size.height;

   visible = true;
   defaultCloseOperation = JFrame.EXIT_ON_CLOSE;
   size = SwingUtil.dimension(300, 300);
   layout = null; // turns off swing's automatic layout

   public static class converter {
      double value1, value2;
      String unit1, unit2;
      public String toString() {
         return unit1 + "/" + unit2;
      }
   }

   public object Converters extends sc.util.ComponentList<converter> {
      object temperature extends converter {
         value1 = 0;
         value2 :=: value1 * 9.0 / 5.0 + 32; 
         unit1 = "Celcius";
         unit2 = "Farenheit";
      }
      object distance extends converter {
         value1 = 0;
         value2 :=: value1 * 0.62137119;
         unit1 = "Kilometers";
         unit2 = "Miles";
      }
   }

   public object converterChoice extends JComboBox {
      int alignx = 20;
      items = Converters;
      location := SwingUtil.point(xpad+alignx, ypad);
      size := SwingUtil.dimension(windowWidth - 2*(xpad+alignx), preferredSize.height);
   }

   //converter currentConverter := Converters.get(converterChoice.selectedIndex);
   converter currentConverter := (converter) converterChoice.selectedItem;

   int row1y := (int)(ypad + converterChoice.location.y + converterChoice.size.height);

   public object unit1Label extends JLabel {
      text := currentConverter.unit1;
      labelFor = unit1Field;
      location := SwingUtil.point(xpad, row1y + baseline);
      size := preferredSize;
   }
   public object unit1Field extends JTextField {
      location := SwingUtil.point(xpad + unit1Label.preferredSize.width + gap, row1y);
      size := SwingUtil.dimension(windowWidth - unit1Label.size.width - xpad - 2*gap, preferredSize.height);
      text :=: numberConverter.numberToString(currentConverter.value1);
   }

   int row2y := (int)(unit1Field.location.y + unit1Field.size.height + ypad);

   public object unit2Label extends JLabel {
      text := currentConverter.unit2;
      labelFor = unit2Field;
      size := preferredSize;
      location := SwingUtil.point(unit1Label.location.x, row2y + baseline);
   }
   public object unit2Field extends JTextField {
      location := SwingUtil.point(xpad + unit2Label.preferredSize.width + gap, row2y);
      size := SwingUtil.dimension(windowWidth - unit2Label.size.width - xpad - 2*gap, preferredSize.height);
      text :=: numberConverter.numberToString(currentConverter.value2);
   }

   public object errorLabel extends JLabel {
      text := numberConverter.error;
      location := SwingUtil.point(xpad, unit2Field.location.y + unit2Field.size.height + ypad + baseline);
      size := preferredSize;
   }

   public object numberConverter extends sc.util.NumberConverter {
   }
}
