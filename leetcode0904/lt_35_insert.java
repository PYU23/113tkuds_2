// 檔名：lt_35_insert.java

class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left; // 插入位置
    }

    // 測試用 main
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {1,3,5,6};
        System.out.println("Target 5 -> Index: " + sol.searchInsert(nums1, 5)); // 2

        int[] nums2 = {1,3,5,6};
        System.out.println("Target 2 -> Index: " + sol.searchInsert(nums2, 2)); // 1

        int[] nums3 = {1,3,5,6};
        System.out.println("Target 7 -> Index: " + sol.searchInsert(nums3, 7)); // 4

        int[] nums4 = {1,3,5,6};
        System.out.println("Target 0 -> Index: " + sol.searchInsert(nums4, 0)); // 0
    }
}
