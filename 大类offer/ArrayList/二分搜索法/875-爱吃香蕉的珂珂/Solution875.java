public class Solution875 {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = getMax(piles) + 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (canEating(piles, mid, h)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canEating(int[] piles, int mid, int h) {
        int times = 0;
        for (int pile : piles) {
            times += (pile / mid) + (pile % mid > 0 ? 1 : 0);
        }
        return times <= h;
    }

    private int getMax(int[] piles) {
        int max = piles[0];
        for (int i = 1; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        return max;
    }
}
