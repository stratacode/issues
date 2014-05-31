public class Base1 {

   public ComponentList<HashMap<String,List<String>>> testList = new ComponentList<HashMap<String,List<String>>>();
   public HashMap<String,List<String>> hm = new HashMap<String,List<String>>();
   { 
      testList.add(hm); 
      List<String> l1 = new ComponentList<String>();
      l1.add("subName00");
      l1.add("subName1");
      hm.put("name1", l1);
      List<String> l2 = new ComponentList<String>();
      l2.add("subName2");
      l2.add("subName3");
      hm.put("name2", l2);
   }

   int currentElement = 0, currentSubElement = 0;
   String currentName = "name1";

   Base1 recurseRef;

   // null int
   int foo := recurseRef.currentName.length();

   public String testBinding := testList.size() == 0 ? "<noList>" : testList.get(currentElement).get(currentName).get(currentSubElement);

   @MainSettings(test=true)
   public static void main(String[] args) {
      Base1 b1 = new Base1();

      //System.out.println("*** <noList>=" + b1.testBinding);

      System.out.println("*** null=" + b1.testBinding);

      b1.currentName = "name1";

      System.out.println("*** subName0=" + b1.testBinding);

      b1.currentElement = 0;

      System.out.println("*** subName0=" + b1.testBinding);

      b1.currentSubElement = 1;

      System.out.println("*** subName1=" + b1.testBinding);
   }
}
