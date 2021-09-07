
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Solution58 {
    public String reverseWords(String s) {
        String[] split = s.split(" ");
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(split));
        Collections.reverse(strings);
        return strings.stream().map(String::trim).filter(trim -> !trim.equals("")).collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        Solution58 solution58 = new Solution58();
        System.out.println(solution58.reverseWords("the sky is blue"));
    }
}
