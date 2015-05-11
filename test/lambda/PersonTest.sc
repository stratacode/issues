import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class PersonTest {
   @Test 
   void testPeople() {
      List<Person> people = Arrays.asList(new Person("Tesla", LocalDate.of(1856, 7, 10), Person.Sex.MALE, "pigen@me.com"), new Person("Edison", LocalDate.of(1847, 2, 11), Person.Sex.MALE, "thomas@edison.com"),
                                          new Person("Curie", LocalDate.of(1867, 11, 7), Person.Sex.FEMALE, "marie@curie.com"));
      System.out.println("*** People: " + people);
      Person.processList(people, p -> p.getGender() == Person.Sex.MALE && p.getAge() > 50, p -> p.printPerson());
      System.out.println("*** Printed:" + Person.printed + ":");
      assertTrue(Person.printed.toString().equals("TeslaEdison"));
      System.out.println("*** First test passed");
      Person.processList(people, p -> p.getGender() == Person.Sex.FEMALE && p.getAge() > 50, p -> p.printPerson());
      System.out.println("*** Printed: " + Person.printed);
      assertEquals(Person.printed.toString(), "TeslaEdisonCurie");

      people.stream().filter(p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 50 && p.getAge() <= 300)
                            .map(p -> p.getEmailAddress()).forEach(name -> System.out.println(name));
   }
}
