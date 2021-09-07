import java.util.LinkedHashMap;

public class Solution146 {

}
class LRUCache{
    private int capacity;
    private LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        return makeRecently(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            makeRecently(key);
        } else {
            if (cache.size() == capacity){
                cache.remove(cache.keySet().iterator().next());
            }
            cache.put(key, value);
        }
    }

    private int makeRecently(int key){
        Integer value = cache.get(key);
        cache.remove(key);
        cache.put(key, value);
        return value;
    }
}
