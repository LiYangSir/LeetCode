import java.util.*;

/**
 * @author LiYangSir
 * @date 2021/6/17
 */
public class Solution19 {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        s = s.trim();
        boolean numFlag = false;
        boolean eFlag = false;
        boolean dotFlag = false;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                numFlag = true;
            }
            else if (s.charAt(i) == '.' && !dotFlag && !eFlag) {
                dotFlag = true;
            } else if ((s.charAt(i) == 'e' || s.charAt(i) == 'E') && !eFlag && numFlag) {
                eFlag = true;
                numFlag = false;
            } else if ((s.charAt(i) == '+' || s.charAt(i) == '-') && (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {

            } else {
                return false;
            }
        }
        return numFlag;
    }

//    public static void main(String[] args) {
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        Set<Map.Entry<Integer, Integer>> entrySet = hashMap.entrySet();
//        TreeSet<Map.Entry<Integer, Integer>> objects = new TreeSet<>((o1, o2) -> o1.getValue() - o2.getValue());
//        objects.addAll(entrySet);
//    }
}
