public class PersonJava {

  private String name;
  private int age;

  public PersonJava(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "PersonJava{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
