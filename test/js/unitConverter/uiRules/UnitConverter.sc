UnitConverter {
   body {
      unitConverterForm {
         converterChoice {
            optionDataSource = converters;
            selectedIndex :=: currentConverterIndex;
         }
         unit1Field {
            value :=: numberConverter.numberToString(currentConverter.value1);
         }

         unit2Field {
            value :=: numberConverter.numberToString(currentConverter.value2);
         }
      }
   }
}
