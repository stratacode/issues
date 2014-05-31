object BaseObject extends BaseClass {
   f1 = 1001;
   f2 = 2002;

   HashMap futil = new HashMap();

   object InnerObject extends InnerClass {
      if1 = 1001;
      if2 = 2002;
   }

   object InnerObjectWithMethod extends InnerClass {
      if1 = 1001;
      if2 = 2002;

      public void innerObjectMethod(int p1, String p2) {
         System.out.println("*** innerObjectMethod in base");
      }
   }

   object InnerObjectWithField extends InnerObject {
      if1 = 11001;
      if2 = 12002;

      int if3 = 3003;
   }
}
