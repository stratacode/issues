
public class NBind2 {

   ArrayList list = new ArrayList();
   {
      list.add(new Integer(1));
   }

   int listSize := list.size();

   @MainSettings(test=true)
   public static void main(String[]args) {
      NBind2 bd = new NBind2();

      System.out.println("*** 1 == " + bd.listSize);

      ArrayList newList = new ArrayList();
      newList.add(new Integer(2));
      newList.add(new Integer(3));

      bd.list = newList;

      System.out.println("*** 2 == " + bd.listSize);
   }
}
