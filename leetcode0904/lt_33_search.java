// 檔名：lt_33_search.java

class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            // 左半邊有序
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } 
            // 右半邊有序
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    // 測試用 main
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {4,5,6,7,0,1,2};
        System.out.println("Target 0 -> Index: " + sol.search(nums1, 0)); // 4

        int[] nums2 = {4,5,6,7,0,1,2};
        System.out.println("Target 3 -> Index: " + sol.search(nums2, 3)); // -1

        int[] nums3 = {1};
        System.out.println("Target 1 -> Index: " + sol.search(nums3, 1)); // 0

        int[] nums4 = {5,1,3};
        System.out.println("Target 3 -> Index: " + sol.search(nums4, 3)); // 2
    }
}
