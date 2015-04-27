
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.time.LocalDate;

class Person {
   enum Sex {
      MALE, FEMALE
   }

   String name;
   LocalDate birthday;
   Sex gender;
   String emailAddress;

   Person() {
   }

   Person(String name, LocalDate bd, Sex gender, String email) {
      this.name = name;
      this.birthday = bd;
      this.gender = gender;
      this.emailAddress = email;
   }

   String getName() {
      return name;
   }

   int getAge() {
      // approx
      int age = LocalDate.now().getYear() - birthday.getYear();
      System.out.println("*** age: " + age);
      return age;
   }

   LocalDate getBirthday() {
      return birthday;
   }

   Sex getGender() {
      return gender;
   }

   String getEmailAddress() {
      return emailAddress;
   }

   static StringBuilder printed = new StringBuilder();
   void printPerson() {
      System.out.println("Name=" + name);
      System.out.println("Gender=" + gender);
      printed.append(name);
   }

   static void processList(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
       for (Person p : roster) {
           if (tester.test(p)) {
              block.accept(p);
           }
       }
   }
}
