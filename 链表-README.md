## 链表
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