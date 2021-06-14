public class Solution5 {
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String palindrome = isPalindrome(s, i);
            if (res.length() < palindrome.length()) {
                res = palindrome;
            }
        }
        return res;
    }

    private String isPalindrome(String s, int i) {
        int left = i, right = i;
        int n = s.length();
        while (left > 0 && s.charAt(left) == s.charAt(left - 1)) {
            left--;
        }
        while (right < n - 1 && s.charAt(right) == s.charAt(right + 1)) {
            right++;
        }
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
        System.out.println(solution5.longestPalindrome("abbbb"));
    }
}
