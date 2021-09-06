import cn.quguai.ListNode;

/**
 * @author LiYangSir
 * @date 2021/6/17
 */
public class Solution18 {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode pre = dump;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
                break;
            }
            pre = pre.next;
        }
        return dump.next;
    }
}
