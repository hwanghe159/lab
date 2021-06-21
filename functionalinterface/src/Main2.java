public class Main2 {

  public static void main(String[] args) {
    Cache<String, Integer> cache = new Cache<>((map, eldest) -> map.size() > 3);
    cache.put("1", 1);
    cache.put("2", 2);
    cache.put("3", 3);
    cache.put("4", 4);
    System.out.println(String.join(", ", cache.keySet()));
  }
}
