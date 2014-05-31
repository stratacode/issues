BaseObject {
   f3 = 300003;

   InnerObject {
      if1 = 100001;
      if2 = 200002;
   }

   InnerObjectWithField {
      if3 = 300003;
   }
   
   InnerObjectWithMethod {
      if2 = 200002;

      public void innerObjectMethod(int p1, String p2) {
         System.out.println("*** innerObjectMethod in subsub");
         super.innerObjectMethod(p1, p2);
      }
   }
}
