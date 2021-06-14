public class Solution234 {

    private Solution82.ListNode left;
    public boolean isPalindrome(Solution82.ListNode head) {
        left = head;
        return traverse(head);
    }

    private boolean traverse(Solution82.ListNode head) {
        if (head == null) return true;
        boolean res = traverse(head.next);
        res = res && (left.val == head.val);
        left = left.next;
        return res;
    }

    public static void main(String[] args) {
        Solution234 solution234 = new Solution234();
    }
}
