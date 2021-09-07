public class Solution56 {
    /**
     * 1. 全部进行异或，所得结果是不同的两个数字异或
     * 2. 获取flag 最后一个1的位置，将其中分为两组，相同的数字必然在一组，不同的必定在两个组
     * 3. 对其中一个组进行异或，获得其中一个值
     * 4. 根据自反性获得另外一个值
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        int flag = res & (-res);
        int x = 0;
        for (int num : nums) {
            if ((num & flag) != 0) {
                x ^= num;
            }
        }
        return new int[]{x, x ^ res};
    }
}
