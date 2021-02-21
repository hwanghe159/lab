import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {

    private List<Person> people;

    @BeforeEach
    void setUp() {
        Person person1 = new Person("김OO");
        Person person2 = new Person("이OO");
        Person person3 = new Person("김OO");
        Person person4 = new Person("최OO");

        people = Arrays.asList(person1, person2, person3, person4);
    }

    @Test
    void test() {
        List<Person> onlyKim = people.stream()
                .filter(new Predicate<Person>() {
                    @Override
                    public boolean test(Person person) {
                        return person.getName().startsWith("김");
                    }
                })
                .collect(toList());

        assertThat(onlyKim).hasSize(2);
    }

    @Test
    void test2() {
        List<Person> onlyKim = people.stream()
                .filter(person -> person.getName().startsWith("김"))
                .collect(toList());

        assertThat(onlyKim).hasSize(2);
    }

    @Test
    void test3() {
        List<Person> onlyKim = people.stream()
                .filter(Person::isLastNameKim)
                .collect(toList());

        assertThat(onlyKim).hasSize(2);
    }


    @Test
    void name() {
        Map<String, Integer> counts = new HashMap<>();
        for (Person person : people) {
            counts.merge(person.getName(), 1, (existingValue, providedValue) -> existingValue + providedValue);
        }
        System.out.println(counts);
    }
}
