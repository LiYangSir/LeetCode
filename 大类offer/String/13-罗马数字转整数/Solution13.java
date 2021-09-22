public class Solution13 {

    public int romanToInt(String s) {
        int sum = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int pre = getValue(s.charAt(i));
            int cur = getValue(s.charAt(i + 1));
            if (pre < cur) {
                sum -= pre;
            } else {
                sum += pre;
            }
        }
        sum += getValue(s.charAt(s.length() - 1));
        return sum;
    }

    private int getValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Solution13 solution13 = new Solution13();
        System.out.println(solution13.romanToInt("MCMXCIV"));
    }
}
