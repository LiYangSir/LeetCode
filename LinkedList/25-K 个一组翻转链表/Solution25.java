public class Solution25 {
    public Solution82.ListNode reverseKGroup(Solution82.ListNode head, int k) {
        if (head == null || head.next == null) return head;
        Solution82.ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) return head;
            b = b.next;
        }
        Solution82.ListNode newHead = reverse2(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }
    // 遍历方式
    private Solution82.ListNode reverse(Solution82.ListNode a, Solution82.ListNode b) {
        Solution82.ListNode pre = null, cur = a, nxt = a;
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
    //  递归方式
    private Solution82.ListNode reverse2(Solution82.ListNode a, Solution82.ListNode b) {
        if (a.next == b) return a;
        Solution82.ListNode newH = reverse2(a.next, b);
        a.next.next = a;
        a.next = null;
        return newH;
    }

    public static void main(String[] args) {
        Solution25 solution25 = new Solution25();
        Solution82.ListNode l1 = new Solution82.ListNode(1);
        Solution82.ListNode l2 = new Solution82.ListNode(2);
        Solution82.ListNode l3 = new Solution82.ListNode(3);
        Solution82.ListNode l4 = new Solution82.ListNode(4);
        Solution82.ListNode l5 = new Solution82.ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        Solution82.ListNode listNode = solution25.reverseKGroup(l1, 2);
        System.out.println(listNode);

    }

}
