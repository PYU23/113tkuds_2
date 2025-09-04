import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int remain, int start, List<Integer> comb, List<List<Integer>> res) {
        if (remain < 0) return;
        if (remain == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            comb.add(candidates[i]);
            backtrack(candidates, remain - candidates[i], i, comb, res);
            comb.remove(comb.size() - 1);
        }
    }

    // 測試用 main
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] candidates1 = {2,3,6,7};
        int target1 = 7;
        System.out.println("Combinations for target " + target1 + ": " + sol.combinationSum(candidates1, target1));
        // [[2,2,3],[7]]

        int[] candidates2 = {2,3,5};
        int target2 = 8;
        System.out.println("Combinations for target " + target2 + ": " + sol.combinationSum(candidates2, target2));
        // [[2,2,2,2],[2,3,3],[3,5]]
    }
}
