import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 排序方便去重
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int remain, int start, List<Integer> comb, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue; // 去重
            if (candidates[i] > remain) break; // 剪枝
            comb.add(candidates[i]);
            backtrack(candidates, remain - candidates[i], i + 1, comb, res);
            comb.remove(comb.size() - 1);
        }
    }

    // 測試用 main
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] candidates1 = {10,1,2,7,6,1,5};
        int target1 = 8;
        System.out.println("Combinations for target " + target1 + ": " + sol.combinationSum2(candidates1, target1));
        // [[1,1,6],[1,2,5],[1,7],[2,6]]

        int[] candidates2 = {2,5,2,1,2};
        int target2 = 5;
        System.out.println("Combinations for target " + target2 + ": " + sol.combinationSum2(candidates2, target2));
        // [[1,2,2],[5]]
    }
}
