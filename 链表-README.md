## 链表
## 82-删除排序链表中重复元素II

给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中没有重复出现的数字。

示例1:
> 输入: 1->2->3->3->4->4->5
> 输出: 1->2->5

示例2:
>输入: 1->1->1->2->3
>输出: 2->3
```java
public class Solution82 {

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
```

## 92. 反转链表Ⅱ

```java
public class Solution92 {
    private ListNode successor = null;
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    private ListNode reverseN(ListNode head, int n) {  // 这是反转0 - n的链表
        if (n == 1){
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }
}
```

## 141. 环形链表

```java
public class Solution141 {
    public boolean hasCycle(Solution82.ListNode head) {
        Solution82.ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
```

## 142. 环形链表Ⅱ(交点)

```java
public class Solution142 {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        if (fast == null || fast.next == null) return null;
        slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
```