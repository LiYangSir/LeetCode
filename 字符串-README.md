## String

### 滑动窗口

模板

```java
for (int i = 0; i < s.length(); i++) {
    char c = s.charAt(i);
    windows.add(s[i]);
    while(vaild){
        window.remove(s[left]);
        left++;
    }
}
```

#### 3. 无重复字符的最长字串

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:
> 输入: s = "abcabcbb"
> 输出: 3 
> 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:
> 输入: s = "bbbbb"
> 输出: 1
> 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:
> 输入: s  "pwwkew"
> 输出: 3
> 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
>      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

示例 4:
> 输入: s = ""
> 输出: 0

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cache.containsKey(c)) {
                left = Math.max(left, cache.get(c) + 1);
            }
            max = Math.max(max, i - left + 1);
            cache.put(c, i);
        }
        return max;
    }
}
```



#### 76. 最小覆盖子串

给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。

示例 1：

> 输入：s = "ADOBECODEBANC", t = "ABC"
> 输出："BANC"

示例 2：

> 输入：s = "a", t = "a"
> 输出："a"

```java
class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();
        for (char c : t.toCharArray()) {
            needs.put(c, needs.computeIfAbsent(c, k -> 0) + 1);
        }
        int left = 0;
        int match = 0;
        int minLen = s.length() + 1;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);   // 1.
            windows.put(c, windows.computeIfAbsent(c, k -> 0) + 1); // 2.
            if(windows.get(c).equals(needs.get(c)))
                match++;
            while (match == needs.size()) {
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    start = left;
                }
                char c1 = s.charAt(left);
                windows.put(c1, windows.get(c1) - 1);
                if (needs.containsKey(c1) && windows.get(c1) < needs.get(c1)) {
                    match--;
                }
                left++;
            }
        }
        return minLen > s.length() ? "" : s.substring(start, start + minLen);
    }
}
```



## 43 字符串相乘

给定两个以字符串形式表示的非负整数num1和num2，返回num1和num2的乘积，它们的乘积也表示为字符串形式。

示例 1:
>输入: num1 = "2", num2 = "3"
>输出: "6"

示例2:
>输入: num1 = "123", num2 = "456"
>输出: "56088"

-> 依赖一个数组（维护这个数组：一直都是从后往前进行遍历），数字的大小为字符串长度之和，需判断首个是否为零

```java
public class Solution43 {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int l1 = num1.length();
        int l2 = num2.length();
        int[] res = new int[l1 + l2];
        for (int i = l1 - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = l2 - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                res[i + j + 1] += x * y;  // 定位到数组的位置
            }
        }
        for (int i = l1 + l2 - 1; i >= 1; i--) {  // 保证数组内容大小小于10
            res[i - 1] += res[i] / 10;
            res[i] = res[i] % 10;
        }
        int index = res[0] == 0 ? 1 : 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (index < l1 + l2) {
            stringBuilder.append(res[index]);
            index++;
        }
        return stringBuilder.toString();
    }
}

```

## 20. 有效的括号

给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。

```java
public class Solution20 {
    public boolean isValid(String s) {
        Stack<Character> ss = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                ss.push(c);
            }else{
                if(ss.peek() == null){
                    return false;
                }
                Character pop = ss.pop();
                if (pop != c - 1 && pop != c - 2) {
                    return false;
                }
            }
        }
        return ss.empty();
    }
}
```
## 17. 电话号码的字母组合

给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。


示例 1：

>输入：digits = "23"
>输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

示例 2：
>输入：digits = ""
>输出：[]

示例 3：
>输入：digits = "2"
>输出：["a","b","c"]

提示：
>0 <= digits.length <= 4
>digits[i] 是范围 ['2', '9'] 的一个数字。

```java
public class Solution17 {
    public List<String> letterCombinations(String digits) {
        if(digits.equals("")){
            return new ArrayList<String>();
        }
        String[] temp = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> list = new ArrayList<>();
        letterCombinations(digits, temp, 0, "", list);
        return list;
    }
    public void letterCombinations(String digits,String[] temp, int index, String str, List<String> list) {
        if(index == digits.length()){
            list.add(str);
            return;
        }
        char c = digits.charAt(index);
        String s  = temp[c - '0'];
        index++;
        for(char c1 : s.toCharArray()){
            letterCombinations(digits, temp, index, str + c1, list);
        }
    }
}
```

## 12. 整数转罗马

罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。

示例 1:

> 输入: 3
> 输出: "III"

示例 2:

> 输入: 4
> 输出: "IV"

示例 3:

> 输入: 9
> 输出: "IX"

示例 4:

> 输入: 58
> 输出: "LVIII"
> 解释: L = 50, V = 5, III = 3.

示例 5:

> 输入: 1994
> 输出: "MCMXCIV"
> 解释: M = 1000, CM = 900, XC = 90, IV = 4.

```java
public class Solution12 {
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length && num >= 0; i++) {
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
```



## 13. 罗马数字转整数

示例 1:

> 输入: "III"
> 输出: 3

示例 2:

>输入: "IV"
>输出: 4

示例 3:
输入: "IX"
输出: 9

示例 4:

> 输入: "LVIII"
> 输出: 58
> 解释: L = 50, V= 5, III = 3.

示例 5:

> 输入: "MCMXCIV"
> 输出: 1994
> 解释: M = 1000, CM = 900, XC = 90, IV = 4.

```java
public class Solution13 {
    public int romanToInt(String s) {
        int sum = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int pre = getValue(s.charAt(i));
            int cur = getValue(s.charAt(i + 1));
            if (pre < cur) {
                sum -= pre;
            } else {
                sum += pre;
            }
        }
        sum += getValue(s.charAt(s.length() - 1));
        return sum;
    }
//MCMXCIV 可以看作： 1000  -100 1000 -10 100 -1 5 的和，只要左边比右边小
    private int getValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
```

