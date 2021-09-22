import cn.quguai.ListNode;

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

    // 三个指针
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dump = new ListNode(-1);
        dump.next = head;

        ListNode temp = dump;
        while (temp.next != null && temp.next.next != null) {
            ListNode cur = temp.next;
            ListNode next = cur.next;
            dump.next = next;
            cur.next = next.next;
            next.next = cur;
            temp = cur;
        }
        return dump.next;
    }
}
