import java.util.*;

class Solution {

    /**
     * 找出所有三元組，使其和為 0，且不重複
     * @param nums 整數陣列
     * @return List of triplets
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;

        Arrays.sort(nums); // 先排序，方便去重與雙指針

        for (int i = 0; i < nums.length - 2; i++) {
            // 避免重複 i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    // 跳過重複的 left
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    // 跳過重複的 right
                    while (left < right && nums[right] == nums[right + 1]) right--;

                } else if (sum < 0) {
                    left++; // 總和太小，左指針右移
                } else {
                    right--; // 總和太大，右指針左移
                }
            }
        }

        return res;
    }

    // 測試 main
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] test1 = {-1,0,1,2,-1,-4};
        int[] test2 = {0,1,1};
        int[] test3 = {0,0,0};

        System.out.println(sol.threeSum(test1)); // [[-1,-1,2],[-1,0,1]]
        System.out.println(sol.threeSum(test2)); // []
        System.out.println(sol.threeSum(test3)); // [[0,0,0]]
    }
}
