import java.util.ArrayDeque;
import java.util.Deque;

public class Solution23 {

    public static String removeRepeat(String s){
        char[] array = s.toCharArray();
        int[] nextIndex = new int[26];
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < array.length; i++) {
            nextIndex[array[i] - 'a'] = i;
        }
        boolean[] hasData = new boolean[26];
        for (int i = 0; i < array.length; i++) {
            if(hasData[array[i] - 'a']){
                continue;
            }
            while(!stack.isEmpty() && stack.peekLast() > array[i] && nextIndex[stack.peekLast() - 'a'] > i){
                Character last = stack.removeLast();
                hasData[last - 'a'] = false;
            }
            stack.add(array[i]);
            hasData[array[i] - 'a'] = true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : stack) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeRepeat("cbacdcbc"));
    }
}
