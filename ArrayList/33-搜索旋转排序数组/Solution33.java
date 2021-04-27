public class Solution33 {
    public int search(int[] nums, int target) {
        if(nums.length == 1)
            return nums[0] == target? 0: -1;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target)
                return mid;
            if(nums[left] <= nums[mid]){
                if(nums[left] <= target && nums[mid] > target){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(nums[mid] < target && nums[right] >= target){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}