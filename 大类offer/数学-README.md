# 数学

[toc]

# 基本计算器

## 224-基本计算器

```java
public class Solution224 {
    public int calculate(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray())
            stack.offer(c);
        return calculate(s, stack);
    }

    private int calculate(String s, Deque<Character> stack) {
        Deque<Integer> ss = new ArrayDeque<>();
        int num = 0;
        char sign = '+';
        while (!stack.isEmpty()) {
            char pop = stack.poll();
            if (isDigital(pop)) {
                num = num * 10 + (pop - '0');
            }
            if (pop == '(') {
                num = calculate(s, stack);
            }
            if (!isDigital(pop) && pop != ' ' || stack.isEmpty()){
                switch (sign) {
                    case '+':
                        ss.push(num);
                        break;
                    case '-':
                        ss.push(-num);
                        break;
                    case '*':
                        Integer pre = ss.pop();
                        ss.push(num * pre);
                        break;
                    case '/':
                        Integer pre2 = ss.pop();
                        ss.push(pre2 / num);
                        break;
                }
                num = 0;
                sign = pop;
            }
            if (pop == ')') break;
        }
        return sum(ss);
    }

    private int sum(Deque<Integer> ss) {
        int sum = 0;
        for (Integer s : ss) {
            sum += s;
        }
        return sum;
    }

    private boolean isDigital(char pop) {
        return (pop - '0') < 10 && (pop - '0') >= 0;
    }
}
```

## 227-基本计算器 II

```java
public class Solution227 {
    public int calculate(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray())
            stack.offer(c);
        return calculate(s, stack);
    }

    private int calculate(String s, Deque<Character> stack) {
        Deque<Integer> ss = new ArrayDeque<>();
        int num = 0;
        char sign = '+';
        while (!stack.isEmpty()) {
            char pop = stack.poll();
            if (isDigital(pop)) {
                num = num * 10 + (pop - '0');
            }
            if (!isDigital(pop) && pop != ' ' || stack.isEmpty()){
                switch (sign) {
                    case '+':
                        ss.push(num);
                        break;
                    case '-':
                        ss.push(-num);
                        break;
                    case '*':
                        Integer pre = ss.pop();
                        ss.push(num * pre);
                        break;
                    case '/':
                        Integer pre2 = ss.pop();
                        ss.push(pre2 / num);
                        break;
                }
                num = 0;
                sign = pop;
            }
        }
        return sum(ss);
    }

    private int sum(Deque<Integer> ss) {
        int sum = 0;
        for (Integer s : ss) {
            sum += s;
        }
        return sum;
    }

    private boolean isDigital(char pop) {
        return (pop - '0') < 10 && (pop - '0') >= 0;
    }
}
```



# 位运算

1.利用或操作 | 和空格将英文字符转换为小写

>('a' | ' ') = 'a'
('A' | ' ') = 'a'

2.利用与操作 & 和下划线将英文字符转换为大写

>('b' & '_') = 'B'
('B' & '_') = 'B'

3.利用异或操作 ^ 和空格进行英文字符大小写互换

>('d' ^ ' ') = 'D'
>('D' ^ ' ') = 'd'

以上操作能够产生奇特效果的原因在于 ASCII 编码。字符其实就是数字，恰巧这些字符对应的数字通过位运算就能得到正确的结果，有兴趣的读者可以查 ASCII 码表自己算算，本文就不展开讲了。

4. 判断两个数是否异号
> int x = -1, y = 2;
> bool f = ((x ^ y) < 0); // true

>int x = 3, y = 2;
bool f = ((x ^ y) < 0); // false

> n & (n-1) 这个操作是算法中常见的，作用是消除数字 n 的二进制表示中的最后一个 1。

## 191. 位1的个数

编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。

示例 1：
>输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。

```java
public class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }
}
```

## 231. 2的幂

给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

示例 1:
>输入: 1
输出: true
解释: 20 = 1

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }
}
```

# 29-两数相除

给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

整数除法的结果应当截去（truncate）小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2

示例 1:

> 输入: dividend = 10, divisor = 3
>
> 输出: 3
> 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3

示例 2:

> 输入: dividend = 7, divisor = -3
> 输出: -2
> 解释: 7/-3 = truncate(-2.33333..) = -2

```java
public class Solution29 {
    public int divide(int dividend, int divisor) {
        int res = 0;
        if(dividend >  divisor){
            while(dividend - divisor >= 0){
                dividend -= divisor;
                res++;
            }
        }else{
            while((dividend < 0 && dividend + divisor <=0) || (dividend > 0 && dividend + divisor >=0)){
                dividend += divisor;
                res--;
            }
        }
        return res;
    }
}
```

# 204-计算质数

统计所有小于非负整数 n 的质数的数量。

示例 1：

> 输入：n = 10
> 输出：4
> 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。

示例 2：

> 输入：n = 0
> 输出：0

示例 3：

> 输入：n = 1
> 输出：0

```java
public class Solution204 {
    public int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);

        for (int i = 2; i * i < n; i++) { // 截取一半
            if (isPrim[i]) {
                for (int j = i * i; j < n; j += i) { // 成倍数的增加
                    isPrim[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) count++;
        }
        return count;
    }
}

```

# 633-平方数之和

给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。

示例 1：

> 输入：c = 5
> 输出：true
> 解释：1 * 1 + 2 * 2 = 5

示例 2：

> 输入：c = 3
> 输出：false

```java
public class Solution633 {
    public boolean judgeSquareSum(int c) {
        int size = (int) Math.sqrt(c);
        for (int i = 0; i <= size; i++) {
            int b = (int) Math.sqrt(c - i * i);
            if (b * b == c - i * i)
                return true;
        }
        return false;
    }
}
```

