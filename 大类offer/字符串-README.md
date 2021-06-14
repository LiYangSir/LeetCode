## String

## 滑动窗口

模板

```c++
for (int i = 0; i < s.length(); i++) {
    char c = s.charAt(i);
    windows.add(s[i]);
    while(vaild){
        window.remove(s[left]);
        left++;
    }
}
```

### 3. 无重复字符的最长字串

给定一个字符串，请你找出其中不含有重复字符的`最长子串`的长度。

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



### 76. 最小覆盖子串

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

## KMP

### 28. 实现strStr()

```java
public class Solution28 {
    private String pat;
    private int[][] dp;

    public void init(String pat) {   // 类似于动态规划
        this.pat = pat;
        int length = pat.length();
        this.dp = new int[length][256];
        // base case
        dp[0][pat.charAt(0)] = 1;
        // 构建数组
        int X = 0;
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < 256; j++)
                dp[i][j] = dp[X][j];
            dp[i][pat.charAt(i)] = i + 1;
            X = dp[X][pat.charAt(i)];
        }
    }
    public int search(String txt){
        int M = pat.length();
        int N = txt.length();
        int j = 0;
        for (int i = 0; i < N; i++) {
            j = dp[j][txt.charAt(i)];
            if (j == M) return i - M + 1;
        }
        return -1;
    }
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) return -1;
        if (needle.equals("")) return 0;
        init(needle);
        return search(haystack);
    }
}

```



## 5. 最长回文子串

给你一个字符串 s，找到 s 中最长的回文子串。

示例 1：

> 输入：s = "babad"
> 输出："bab"

示例 2：

> 输入：s = "cbbd"
> 输出："bb"

示例 3：

> 输入：s = "a"
> 输出："a"

示例 4：

> 输入：s = "ac"
> 输出："a"

```java
public class Solution5 {
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String palindrome = isPalindrome(s, i);
            if (res.length() < palindrome.length()) {
                res = palindrome;
            }
        }
        return res;
    }

    private String isPalindrome(String s, int i) {
        int left = i, right = i;
        int n = s.length();
        while (left > 0 && s.charAt(left) == s.charAt(left - 1)) {
            left--;
        }
        while (right < n - 1 && s.charAt(right) == s.charAt(right + 1)) {
            right++;
        }
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
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
        for (int i = 0; i < values.length && num >= 0; i++) { // && num >= 0;
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



## 38-外观数列

给定一个正整数 n ，输出外观数列的第 n 项。

「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。

你可以将其视作是由递归公式定义的数字字符串序列：

countAndSay(1) = "1"
countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
前五项如下：

```
1.     1
2.     11
3.     21
4.     1211
5.     111221
       第一项是数字 1 
       描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
       描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
       描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
       描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
```

```java
class Solution38 {
    public String countAndSay(int n) {
        if(n <= 1){
            return "1";
        }
        String data = countAndSay(n - 1);
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < data.length(); i++){
            int count = 1;
            char c = data.charAt(i);
            while(i + 1 < data.length() && c == data.charAt(i + 1)){
                count++;
                i++;
            }
            res.append(count).append(c);
        }
        return res.toString();
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

## 151-反转字符串里面的单词

给你一个字符串 s ，逐个翻转字符串中的所有 单词 。

单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。

请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。

说明：

输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
翻转后单词间应当仅用一个空格分隔。
翻转后的字符串中不应包含额外的空格。


示例 1：

> 输入：s = "the sky is blue"
> 输出："blue is sky the"

示例 2：

> 输入：s = "  hello world  "
> 输出："world hello"
> 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。

```java
public class Solution151 {
    public static String reverseWords(String s) {
        String[] split = s.split(" ");
        ArrayList<String> arrayList = new ArrayList<>();
        for (int length = split.length - 1; length >= 0; length--) {
            if (!split[length].equals("")) {
                arrayList.add(split[length]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            stringBuilder.append(arrayList.get(i));
            if (i != arrayList.size() - 1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}

```

