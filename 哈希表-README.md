## 哈希表

### 49. 字母异位词分组

给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:

>输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
>输出:
>[
>  ["ate","eat","tea"],
>  ["nat","tan"],
>  ["bat"]
>]

```java
public class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
        Map<Long, List<String>> map = new HashMap<>();
        for (String str : strs) {
            long key = 1;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                key *= prime[c - 'a'];
            }
            if (!map.containsKey(key))
                map.put(key, new ArrayList<>());
            List<String> strings = map.get(key);
            strings.add(str);
        }
        List<List<String>> res = new ArrayList<>();
        for (Long aLong : map.keySet()) {
            res.add(map.get(aLong));
        }
        return res;
    }
}
```