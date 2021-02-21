public class Person {

    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public boolean isLastNameKim() {
        return this.name.startsWith("김");
    }

    public void printName() {
        System.out.printf("제 이름은 %s입니다.\n", this.name);
    }

    public String getName() {
        return name;
    }
}
