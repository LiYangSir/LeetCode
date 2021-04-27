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
                res[i + j + 1] += x * y;
            }
        }
        for (int i = l1 + l2 - 1; i >= 1; i--) {
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

    public static void main(String[] args) {
        Solution43 solution43 = new Solution43();
        System.out.println(solution43.multiply("123", "456"));
    }
}
