class TempConverter extends JFrame {
   int xpad = 5, ypad = 5;
   static int gap = 10;
   static int baseline = 5;

   visible = true;
   defaultCloseOperation = JFrame.EXIT_ON_CLOSE;
   size = SwingUtil.dimension(300, 300);
   layout = null; // turns off swing's automatic layout

   object celciusLabel extends JLabel {
      text = "Temp Celcius";
      labelFor = celciusField;
      location := SwingUtil.point(xpad, ypad + baseline);
      size := preferredSize;
   }
   object celciusField extends JTextField {
      location := SwingUtil.point(xpad + ((int) celciusLabel.preferredSize.width) + gap, ypad);
      size := SwingUtil.dimension((int)TempConverter.this.size.width - (int)celciusLabel.size.width - xpad - 2*gap, (int)preferredSize.height);;
   }

   object farenheitLabel extends JLabel {
      text = "Temp Farenheit";
      labelFor = farenheitField;
      size = preferredSize;
      location := SwingUtil.point((int)celciusLabel.location.x, celciusField.y + celciusField.height + ypad + baseline);
   }
   object farenheitField extends JTextField {
      location := SwingUtil.point(xpad + ((int) farenheitLabel.preferredSize.width) + gap, celciusField.height + ypad);
      size := SwingUtil.dimension((int)TempConverter.this.size.width - (int)farenheitLabel.size.width - xpad - 2*gap, (int)preferredSize.height);
      text :=: numberConverter.numberToString(numberConverter.stringToNumber(celciusField.text) * 9.0 / 5.0 + 32);
   }

   object errorLabel extends JLabel {
      text := numberConverter.error;
      location := SwingUtil.point((int) celciusLabel.location.x, (int)(farenheitField.location.y + farenheitField.size.height + ypad + baseline));
      size := preferredSize;
   }

   object numberConverter extends sc.util.NumberConverter {
   }
}
