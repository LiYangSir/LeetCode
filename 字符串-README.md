## String

### 43 字符串相乘
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

### 20. 有效的括号
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
### 17. 电话号码的字母组合
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