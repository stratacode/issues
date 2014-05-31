public class Base2 {

   public ComponentList<HashMap<String,List<String>>> testList = new ComponentList<HashMap<String,List<String>>>();
   public HashMap<String,List<String>> hm;

   int currentElement = 0, currentSubElement = 0;
   String currentName; 

   Base2 recurseRef;

   // null int
   int foo := recurseRef.currentName.length();

   public String testBinding := testList.size() <= currentElement ? ("Element: " + currentElement + " >= List size: " + testList.size()) : testList.get(currentElement).get(currentName).get(currentSubElement);

   public static void main(String[] args) {
      Base2 b1 = new Base2();

      System.out.println("*** Element: 0 >= List size 0 =" + b1.testBinding);

      b1.hm = new HashMap<String,List<String>>();
      b1.testList.add(b1.hm); 
      List<String> l1 = new ComponentList<String>();
      l1.add("subName0");
      l1.add("subName1");
      b1.hm.put("name1", l1);
      List<String> l2 = new ComponentList<String>();
      l2.add("subName2");
      l2.add("subName3");
      b1.hm.put("name2", l2);

      HashMap<String,List<String>> hm2 = new HashMap<String,List<String>>();
      l1 = new ComponentList<String>();
      l1.add("subName4");
      l1.add("subName5");
      hm2.put("name3", l1);
      l2 = new ComponentList<String>();
      l2.add("subName6");
      l2.add("subName7");
      hm2.put("name4", l2);

      System.out.println("*** null=" + b1.testBinding);

      b1.currentName = "name1";

      System.out.println("*** subName0=" + b1.testBinding);

      b1.currentElement = 0;

      System.out.println("*** subName0=" + b1.testBinding);

      b1.currentSubElement = 1;

      System.out.println("*** subName1=" + b1.testBinding);

      b1.currentSubElement = 0;

      System.out.println("*** subName0=" + b1.testBinding);

      b1.currentName = "name2";

      System.out.println("*** subName2=" + b1.testBinding);

      b1.currentSubElement = 1;

      System.out.println("*** subName3=" + b1.testBinding);

      b1.testList.add(hm2); 

      System.out.println("*** subName3=" + b1.testBinding);

      b1.currentElement = 1;

      System.out.println("*** null=" + b1.testBinding);

      b1.currentName = "name4";

      System.out.println("*** subName7=" + b1.testBinding);

      b1.currentName = "name3";

      System.out.println("*** subName5=" + b1.testBinding);

      b1.currentSubElement = 0;

      System.out.println("*** subName4=" + b1.testBinding);

      b1.currentElement = 2;

      System.out.println("*** Element: 2 >= List size 2 =" + b1.testBinding);

      b1.currentElement = 0;

      System.out.println("*** subName0=" + b1.testBinding); 

      b1.currentName = "name1";

      System.out.println("*** subName0=" + b1.testBinding); 

      b1.currentName = "name2";

      System.out.println("*** subName2=" + b1.testBinding); 
   }
}
