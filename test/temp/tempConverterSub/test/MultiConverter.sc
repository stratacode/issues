MultiConverter {
   //public object converters extends sc.util.ComponentList<Converter> {
   converters {
      object distance extends Converter {
         value1 = 0;
         value2 :=: value1 * 0.62137119;
         unit1 = "Kilometers";
         unit2 = "Miles";
         title = "Distance";
      }
      object speed extends Converter {
         title = "Speed";
         value1 = 0;
         value2 :=: value1 * 1.609344;
         unit1 = "Miles per hour";
         unit2 = "Kilometers per hour";
      }
   }
}
