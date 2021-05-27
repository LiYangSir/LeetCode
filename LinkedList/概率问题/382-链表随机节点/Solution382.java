import java.util.Random;

public class Solution382 {

}
class Solution {

    private Solution82.ListNode head;

    public Solution(Solution82.ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        int res = 0, i = 0;
        Solution82.ListNode p = head;
        Random random = new Random();
        while (p != null) {
            if (random.nextInt(++i) == 0) {
                res = p.val;
            }
            p = p.next;
        }
        return res;
    }
}
