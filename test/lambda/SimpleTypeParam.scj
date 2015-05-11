import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

class SimpleTypeParam {

   public static final List EMPTY_LIST = new ArrayList<>();

   static final <T> List<T> emptyList() {
      return (List<T>) EMPTY_LIST;
   }

   void runTest() {
      List<String> listOne = emptyList();
      //assertTrue(listOne.size() == 0);
   }
}
