import java.util.function.Supplier;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Collection;
import java.util.Arrays;
import java.time.LocalDate;

import java.util.function.Predicate;
import java.util.function.Consumer;
import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;

class ConstRef {
   static <T, SRC extends Collection<T>, DST extends Collection<T>>
       DST copyElements(SRC src, Supplier<DST> supp) {
           DST result = supp.get();
           for (T t : src) {
               result.add(t);
           }
           return result;
   }

   void runTest() {
      List<Person> people = Arrays.asList(new Person("Tesla", LocalDate.of(1856, 7, 10), Person.Sex.MALE, "pigen@me.com"), 
                                          new Person("Edison", LocalDate.of(1847, 2, 11), Person.Sex.MALE, "thomas@edison.com"), 
                                          new Person("Curie", LocalDate.of(1867, 11, 7), Person.Sex.FEMALE, "marie@curie.com"));

      Set<Person> personSet = copyElements(people, () -> { return new HashSet<Person>(); });
      assertEquals(personSet.size(), 3);

      // Constructor
      Set<Person> refSet = copyElements(people, HashSet::new);

      assertEquals(refSet.size(), 3);

      // Constructor with type params
      refSet = copyElements(people, HashSet<Person>::new);
      assertEquals(refSet.size(), 3);
   }

}
