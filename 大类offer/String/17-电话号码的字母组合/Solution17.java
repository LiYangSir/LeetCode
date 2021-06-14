import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        Solution17 solution17 = new Solution17();
        List<String> strings = solution17.letterCombinations("");
        strings.forEach(System.out::println);
    }
}