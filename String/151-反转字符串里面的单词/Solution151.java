import java.util.ArrayList;

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

    public static void main(String[] args) {
        System.out.println(reverseWords("        Hello   World!   "));
    }
}
