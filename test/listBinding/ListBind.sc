public class ListBind {

   object testObj {
      object item1 {
	 public String toString() {
	    return "item1";
	 }
      }
      object item2 {
	 public String toString() {
	    return "item2";
	 }
      }
      List items = new ArrayList();
      {
	 items.add(item1);
	 items.add(item2);
      }
   }

   List listItems := foo(testObj.items);

   List foo(List items) {
      System.out.println("+++ change detected!");
      return items;
   }

   @sc.obj.MainSettings
   public static void main(String[] args) { 
      ListBind lbind = new ListBind();
      System.out.println("testObj.items: " + lbind.testObj.items);
      System.out.println("listItems: " + lbind.listItems);
      System.out.println("deleting first item");
      lbind.testObj.items.remove(0);
      System.out.println("testObj.items: " + lbind.testObj.items);
      System.out.println("listItems: " + lbind.listItems);
   }
}
