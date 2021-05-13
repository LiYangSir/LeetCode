import java.util.ArrayDeque;
import java.util.Deque;

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

    public static void main(String[] args) {
        Solution224 solution224 = new Solution224();
        System.out.println(solution224.calculate("1 + (2 / 1)"));

    }

}
