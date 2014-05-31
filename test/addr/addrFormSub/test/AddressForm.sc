AddressForm {
   xpad := addressPanel.xpadSlider.value;
   ypad := addressPanel.ypadSlider.value;

   // Ooops - baseclass has these as "static" - redefine as not static so we can bind them here
   // You can't always convert from static to instance without errors but when you can it is very useful!
   public int gap := addressPanel.gapSlider.value;
   public int baseline := addressPanel.baselineSlider.value;

   addressPanel {
      int sliderWidth := (int)size.width/4;
      int sliderY := (int) stateField.location.y + (int)stateField.size.height + ypad;
      int sliderHeight = 30;

      cityField {
         text :=: stateField.text;
      }

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
}
