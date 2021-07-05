import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class Main {

  public static void main(String[] args) {
    Person person1 = new Person("김OO", 27);
    Person person2 = new Person("이OO", 29);
    Person person3 = new Person("박OO", 25);

    Optional<Person> oldest = pickOldest(person1, person2, person3);
    System.out.println("가장 늙은 사람은? " + (oldest.isPresent() ? oldest.get().getName() : "결과없음"));
    System.out.println("가장 늙은 사람은? " + (oldest.map(p -> p.getName()).orElse("결과없음")));

    oldest.get().setAge(1);
    System.out.println(person2.getAge());
  }

  public static Optional<Person> pickOldest(Person... people) {
    return Arrays.stream(people)
        .max(Comparator.comparingInt(Person::getAge));
  }
}
