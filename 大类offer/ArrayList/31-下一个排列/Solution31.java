import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

public class Solution31 {
    /**
     * 核心是从后往前找到一个较小的数，也就是突然递减的数
     * 根据这个较小的数，找到一个比它大的数进行替换，保证后面的一个有序性
     * 并反转较小数后面的数，保证数据的递增
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        i--;
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        for (int m = i + 1; m < i + 1 + (nums.length - i - 1) / 2; m++) {
            swap(nums, m, nums.length - m + i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j]  ;
        nums[j] = temp;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 1);
        future = future.thenApply(integer -> {
            System.out.println(integer);
            return 1 / 0;
        });
        future = future.thenApply(integer -> {
            System.out.println(integer);
            return 1;
        });
        future = future.exceptionally(throwable -> throwable == null ? 1 : 0);
        Integer integer = future.get();
        System.out.println(integer);
        Stream.of(1, 2, 3, 2, 1)
                .dropWhile(n -> n < 3)
                .forEach(System.out::println); // [3, 2, 1]
    }
}
