import java.util.Arrays;
import java.util.List;

class methRefStream {

   @Test void runTest() {
      List<String> myList = Arrays.asList("Dan", "Gary", "George", "Andy", "Bruce");
      myList.stream()
       .filter(s -> s.startsWith("G"))
       .map(String::toUpperCase)
       .sorted()
       .forEach(System.out::println);

   }
}
