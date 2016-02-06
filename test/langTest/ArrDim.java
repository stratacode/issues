class ArrDim {
   boolean isEmpty(Object[] vals) {
      return vals.length > 0;
   }

   void foo() {
      String[][] value = new String[][] { new String[] {"a"}, new String[] {"b"} };

      isEmpty(value);
   }
}
