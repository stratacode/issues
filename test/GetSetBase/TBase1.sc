
public class TBase1 {
   public ComponentList<HashMap<String,List<String>>> testList = new ComponentList<HashMap<String,List<String>>>();
   public HashMap<String,List<String>> hm = new HashMap<String,List<String>>();
   { 
      testList.add(hm); 
      List<String> l1 = new ComponentList<String>();
      l1.add("subName0");
      l1.add("subName1");
      hm.put("name1", l1);
      List<String> l2 = new ComponentList<String>();
      l2.add("subName2");
      l2.add("subName3");
      hm.put("name2", l2);
   }

   int currentElement = 0, currentSubElement = 0;
   String currentName = "name1";

   TBase1 recurseRef;

   // null int
   int foo := recurseRef.currentName.length();

   public String testBinding := testList.size() == 0 ? "<noList>" : testList.get(currentElement).get(currentName).get(currentSubElement);

   @Test
   public void doTest() {
      assertTrue(testBinding.equals("subName0"));

      currentName = "name1";

      assertTrue("subName0".equals(testBinding));

      currentElement = 0;

      assertTrue("subName0".equals(testBinding));

      currentSubElement = 1;

      assertTrue("subName1".equals(testBinding));
   }
}
