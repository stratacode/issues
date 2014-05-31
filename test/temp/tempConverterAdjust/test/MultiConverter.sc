MultiConverter {
   xpad := xpadSlider.value;
   ypad := ypadSlider.value;

   gap := gapSlider.value;
   baseline := baselineSlider.value;

   int sliderWidth := (int)size.width/4;
   int sliderY := (int) errorLabel.location.y + (int)errorLabel.size.height + ypad;
   int sliderHeight = 30;

   object xpadSlider extends JSlider {
      minimum = 0;
      maximum = 20;
      orientation = HORIZONTAL;
      value = 10;

      location := SwingUtil.point(xpad, sliderY);
      size := SwingUtil.dimension(sliderWidth, sliderHeight);
   }

   object ypadSlider extends JSlider {
      minimum = 0;
      maximum = 20;
      orientation = HORIZONTAL;
      value = 10;

      location := SwingUtil.point(xpad + sliderWidth, sliderY);
      size := SwingUtil.dimension(sliderWidth, sliderHeight);
   }

   object baselineSlider extends JSlider {
      minimum = 0;
      maximum = 20;
      orientation = HORIZONTAL;
      value = 5;

      location := SwingUtil.point(xpad + 2*sliderWidth, sliderY);
      size := SwingUtil.dimension(sliderWidth, sliderHeight);
   }

   object gapSlider extends JSlider {
      minimum = 0;
      maximum = 20;
      orientation = HORIZONTAL;
      value = 10;

      location := SwingUtil.point(xpad + 3*sliderWidth, sliderY);
      size := SwingUtil.dimension(sliderWidth, sliderHeight);
   }
}
