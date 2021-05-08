public class Solution92 {
    private Solution82.ListNode successor = null;
    public Solution82.ListNode reverseBetween(Solution82.ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    private Solution82.ListNode reverseN(Solution82.ListNode head, int n) {
        if (n == 1){
            successor = head.next;
            return head;
        }
        Solution82.ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }
}
