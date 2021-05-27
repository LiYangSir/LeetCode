public class Solution82 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dump = new ListNode(-1000, head);
        return deleteDuplicates(dump, head);
    }

    private ListNode deleteDuplicates(ListNode pre, ListNode current) {
        if (current == null)
            return null;
        if (current.val == pre.val || (current.next!=null && current.val == current.next.val))
            return deleteDuplicates(current, current.next);
        else{
            current.next = deleteDuplicates(current, current.next);
            return current;
        }
    }
}
