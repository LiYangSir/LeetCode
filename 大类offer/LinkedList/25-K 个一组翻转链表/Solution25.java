import cn.quguai.ListNode;

public class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) return head;
            b = b.next;
        }
        ListNode newHead = reverse2(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }
    // 遍历方式
    private ListNode reverse(ListNode a, ListNode b) {
        ListNode pre = null, cur = a, nxt = a;
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
    //  递归方式
    private ListNode reverse2(ListNode a, ListNode b) {
        if (a.next == b) return a;
        ListNode newH = reverse2(a.next, b);
        a.next.next = a;
        a.next = null;
        return newH;
    }

    public static void main(String[] args) {
        Solution25 solution25 = new Solution25();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ListNode listNode = solution25.reverseKGroup(l1, 2);
        System.out.println(listNode);

    }

}
