## 链表
## 24-两两交换链表中的节点

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例 1：

![img](https://markdown-liyang.oss-cn-beijing.aliyuncs.com/blog/elasticsearch/swap_ex1.jpg)

> 输入：head = [1,2,3,4]
> 输出：[2,1,4,3]

示例 2：

> 输入：head = []
> 输出：[]

示例 3：

> 输入：head = [1]
> 输出：[1]

```java
public class Solution24 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(head.next.next);
        next.next = head;
        return next;
    }
}
```

## 25-K 个一组翻转链表

给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

进阶：

你可以设计一个只使用常数额外空间的算法来解决此问题吗？
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

示例 1：

![img](https://markdown-liyang.oss-cn-beijing.aliyuncs.com/blog/elasticsearch/reverse_ex1.jpg)

> 输入：head = [1,2,3,4,5], k = 2
> 输出：[2,1,4,3,5]

示例 2：

![img](https://markdown-liyang.oss-cn-beijing.aliyuncs.com/blog/elasticsearch/reverse_ex2.jpg)

> 输入：head = [1,2,3,4,5], k = 3
> 输出：[3,2,1,4,5]

示例 3：

> 输入：head = [1,2,3,4,5], k = 1
> 输出：[1,2,3,4,5]

示例 4：

> 输入：head = [1], k = 1
>
> 输出：[1]

```java
public class Solution25 {
    public Solution82.ListNode reverseKGroup(ListNode head, int k) {
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
}
```



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

## 234-回文链表

```java
public class Solution234 {

    private ListNode left;
    public boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    private boolean traverse(ListNode head) {
        if (head == null) return true;
        boolean res = traverse(head.next);
        res = res && (left.val == head.val);
        left = left.next;
        return res;
    }
}
```

