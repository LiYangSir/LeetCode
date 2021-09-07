public class Solution67 {
    public int strToInt(String str) {
        str = str.trim();
        if (str.length() < 1) return 0;
        int sign = 1;
        int start = 1;
        if (str.charAt(0) == '-') {
            sign = -1;
        }else if (str.charAt(0) != '+'){
            start = 0;
        }
        int boundary = Integer.MAX_VALUE / 10;
        int res = 0;
        for (int i = start; i < str.length(); i++) {
            int m = str.charAt(i) - '0';
            if (m < 0 || m > 9) return res;
            if (res > boundary || res == boundary && m > 7) return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            res = res * 10 + m;
        }
        return res * sign;
    }

    public static void main(String[] args) {
        Solution67 solution67 = new Solution67();
        solution67.strToInt("-91283472332");
    }
}
