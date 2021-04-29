public class Solution24 {
    public Solution82.ListNode swapPairs(Solution82.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Solution82.ListNode next = head.next;
        head.next = swapPairs(head.next.next);
        next.next = head;
        return next;
    }
}
