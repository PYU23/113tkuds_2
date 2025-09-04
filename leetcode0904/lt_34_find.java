class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                result = mid;
                if (isFirst) {
                    right = mid - 1; // 找第一個出現
                } else {
                    left = mid + 1; // 找最後一個出現
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    // 測試用 main
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {5,7,7,8,8,10};
        int[] res1 = sol.searchRange(nums1, 8);
        System.out.println("Target 8 -> [" + res1[0] + ", " + res1[1] + "]"); // [3,4]

        int[] nums2 = {5,7,7,8,8,10};
        int[] res2 = sol.searchRange(nums2, 6);
        System.out.println("Target 6 -> [" + res2[0] + ", " + res2[1] + "]"); // [-1,-1]

        int[] nums3 = {};
        int[] res3 = sol.searchRange(nums3, 0);
        System.out.println("Target 0 -> [" + res3[0] + ", " + res3[1] + "]"); // [-1,-1]

        int[] nums4 = {1};
        int[] res4 = sol.searchRange(nums4, 1);
        System.out.println("Target 1 -> [" + res4[0] + ", " + res4[1] + "]"); // [0,0]
    }
}
