import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    public static void main(String[] args) {

    }
}
