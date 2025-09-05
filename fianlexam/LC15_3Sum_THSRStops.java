// 檔名：LC15_3Sum_THSRStops.java

import java.util.*;

public class LC15_3Sum_THSRStops {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        List<List<Integer>> result = threeSum(nums);

        for (List<Integer> triplet : result) {
            System.out.println(triplet.get(0) + " " + triplet.get(1) + " " + triplet.get(2));
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 避免重複答案
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 若當前數字已大於 0，可結束
            if (nums[i] > 0) break;

            int L = i + 1, R = n - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    // 跳過重複 L
                    while (L < R && nums[L] == nums[L + 1]) L++;
                    // 跳過重複 R
                    while (L < R && nums[R] == nums[R - 1]) R--;
                    L++;
                    R--;
                } else if (sum < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }
        return res;
    }
}
