import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HelloJava {

  public static void main(String[] args) {
    List<Person> persons
        = Arrays.asList(new Person("영희", 19, false), new Person("철수", 20, false));
    persons.sort(Comparator.comparing(Person::getAge).reversed());
    System.out.println();
    System.out.println(persons.get(0));

    final String name;
    if (random()) {
      name = "안녕";
    } else {
      name = "하세요";
    }
    System.out.println(name);
  }

  private static boolean random() {
    List<Integer> integers = Arrays.asList(1, 2, 3, 4);
    Collections.shuffle(integers);
    return integers.get(0) % 2 == 0;
  }

}
