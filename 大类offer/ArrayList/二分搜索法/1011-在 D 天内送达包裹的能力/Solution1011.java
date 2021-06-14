public class Solution1011 {
    public int shipWithinDays(int[] weights, int days) {
        int left = getMax(weights);
        int right = getSum(weights) + 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (canShip(weights, mid, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int getSum(int[] weights) {
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
        }
        return sum;
    }

    private int getMax(int[] weights) {
        int max = Integer.MIN_VALUE;
        for (int weight : weights) {
            max = Math.max(weight, max);
        }
        return max;
    }

    private boolean canShip(int[] weights, int mid, int days) {
        int sum = 0;
        int day = 0;
        for (int weight : weights) {
            if (sum + weight > mid) {
                day++;
                sum = 0;
                if (day >= days) return false;
            }
            sum += weight;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution1011 solution1011 = new Solution1011();
        solution1011.shipWithinDays(new int[]{1, 2, 3, 4, 5 , 6, 7, 8, 9, 10}, 5);
    }
}
