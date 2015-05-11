import java.util.Arrays;
import java.time.LocalDate;
import java.util.List;

class methRefInst {
   class ComparisonProvider {
       public int compareByName(Person a, Person b) {
           return a.getName().compareTo(b.getName());
       }
           
       public int compareByAge(Person a, Person b) {
           return a.getBirthday().compareTo(b.getBirthday());
       }
   }

   @Test
   void runTest() {
      List<Person> people = Arrays.asList(new Person("Tesla", LocalDate.of(1856, 7, 10), Person.Sex.MALE, "pigen@me.com"), 
                                          new Person("Edison", LocalDate.of(1847, 2, 11), Person.Sex.MALE, "thomas@edison.com"), 
                                          new Person("Curie", LocalDate.of(1867, 11, 7), Person.Sex.FEMALE, "marie@curie.com"));

      Person[] peopleArray = people.toArray(new Person[people.size()]);
      ComparisonProvider myComparisonProvider = new ComparisonProvider();

      // Test using an instance method:
      // (a, b) -> myComparisonProvider.compareTo(a, b)
      Arrays.sort(peopleArray, myComparisonProvider::compareByName);
      assertEquals(peopleArray[0].getName(), "Curie");

      // Test using an instance method off of a type
      //   (a, b) -> a.compareTo(b)
      String[] stringArray = { "Roy", "Rebel", "Dudley", "Tory"};
      Arrays.sort(stringArray, String::compareToIgnoreCase);
      assertEquals(stringArray[0], "Dudley");
   }
}
