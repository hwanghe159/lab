import java.util.ArrayList;

public class Box<T> {

  ArrayList<T> list = new ArrayList<>();

  void add(T item) {
    list.add(item);
  }

  T get(int i) {
    return list.get(i);
  }

  ArrayList<T> getList() {
    return list;
  }

  int size() {
    return list.size();
  }

  public String toString() {
    return list.toString();
  }
}
