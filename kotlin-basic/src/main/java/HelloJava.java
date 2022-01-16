import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HelloJava {

  public static void main(String[] args) {
    List<Person> persons
        = Arrays.asList(new Person("영희", 19), new Person("철수", 20));
    persons.sort(Comparator.comparing(Person::getAge).reversed());
    System.out.println();
    System.out.println(persons.get(0));
  }

}
