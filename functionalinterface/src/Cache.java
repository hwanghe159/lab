import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiPredicate;

public class Cache<K, V> extends LinkedHashMap<K, V> {

  private final BiPredicate<Map<K, V>, Map.Entry<K, V>> biPredicate;

  public Cache(BiPredicate<Map<K, V>, Map.Entry<K, V>> biPredicate) {
    this.biPredicate = biPredicate;
  }

  @Override
  protected boolean removeEldestEntry(Entry<K, V> eldest) {
    return biPredicate.test(this, eldest);
  }
}
