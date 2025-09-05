import java.util.*;

public class LC40_CombinationSum2_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = sc.nextInt();
        sc.close();

        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        for (List<Integer> combo : res) {
            for (int i = 0; i < combo.size(); i++)
                System.out.print(combo.get(i) + (i < combo.size() - 1 ? " " : ""));
            System.out.println();
        }
    }

    static void backtrack(int[] c, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (remain < 0) return;

        for (int i = start; i < c.length; i++) {
            if (i > start && c[i] == c[i - 1]) continue; // 去重
            path.add(c[i]);
            backtrack(c, remain - c[i], i + 1, path, res); // 不可重複使用
            path.remove(path.size() - 1);
        }
    }
}
