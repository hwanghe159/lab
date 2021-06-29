import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {
    Person person1 = new Person("김OO", 27);
    Person person2 = new Person("이OO", 29);
    Person person3 = new Person("박OO", 25);

    Optional<Person> oldest = pickOldest(person1, person2, person3);
    System.out.println("가장 늙은 사람은? " + (oldest.isPresent() ? oldest.get().getName() : "결과없음"));
    System.out.println("가장 늙은 사람은? " + (oldest.map(p -> p.getName()).orElse("결과없음")));
    Person person = pickOldest(person1, person2, person3)
        .orElse(new Person("홍길동", 0));
    Person personn = pickOldest(person1, person2, person3).orElseGet(() -> new Person("홍길동", 0));
    Person personnn = pickOldest(person1, person2, person3)
        .orElseThrow(() -> new RuntimeException());
    Stream<Optional<Person>> streamOfOptional = Stream.of(Optional.of(new Person("", 0)));
    Stream<Person> personStream = streamOfOptional
        .filter(Optional::isPresent)
        .map(Optional::get);
    Stream<Person> personStream2 = streamOfOptional
        .flatMap(Optional::stream);
  }

  public static Optional<Person> pickOldest(Person... people) {
    return Arrays.stream(people)
        .max(Comparator.comparingInt(Person::getAge));
  }
}
