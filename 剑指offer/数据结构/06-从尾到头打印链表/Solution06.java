package s06_从尾到头打印链表;

import common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiYangSir
 * @date 2021/6/14
 */
public class Solution06 {
    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        dfs(list, head);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfs(List<Integer> list, ListNode head) {
        if (head == null) return;
        dfs(list, head.next);
        list.add(head.val);
    }
}
