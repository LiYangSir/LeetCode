/**
 * @author LiYangSir
 * @date 2021/6/15
 */
public class Solution11 {

    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return numbers[left];
    }


    public static void main(String[] args) {
        Solution11 solution11 = new Solution11();
        System.out.println(solution11.minArray(new int[]{3, 3, 1, 3}));
    }
}
