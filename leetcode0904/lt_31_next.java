// 檔名：lt_31_next.java

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int n = nums.length;
        int i = n - 2;

        // Step 1: 從右往左找第一個遞減元素
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: 如果找到，從右邊找第一個比 nums[i] 大的元素，交換
        if (i >= 0) {
            int j = n - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: 反轉 i+1 之後的數字
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    // 測試用 main
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {1, 2, 3};
        sol.nextPermutation(nums1);
        System.out.print("Next permutation of [1,2,3] -> ");
        for (int num : nums1) System.out.print(num + " ");
        System.out.println();

        int[] nums2 = {2, 3, 1};
        sol.nextPermutation(nums2);
        System.out.print("Next permutation of [2,3,1] -> ");
        for (int num : nums2) System.out.print(num + " ");
        System.out.println();

        int[] nums3 = {3, 2, 1};
        sol.nextPermutation(nums3);
        System.out.print("Next permutation of [3,2,1] -> ");
        for (int num : nums3) System.out.print(num + " ");
        System.out.println();
    }
}
