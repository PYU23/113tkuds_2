import java.util.*;

public class TreePathProblems {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        /*
                範例樹:
                      10
                    /    \
                   5      12
                  / \       \
                 4   7       15
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(15);

        System.out.println("所有根到葉路徑:");
        List<List<Integer>> allPaths = rootToLeafPaths(root);
        for (List<Integer> path : allPaths) {
            System.out.println(path);
        }

        int targetSum = 22;
        System.out.println("\n是否存在根到葉路徑和為 " + targetSum + "? " + hasPathSum(root, targetSum));

        System.out.println("\n根到葉路徑最大和: " + maxRootToLeafSum(root));

        System.out.println("\n任意兩節點間最大路徑和 (樹的直徑加權版): " + maxPathSum(root));
    }

    // 1. 找出從根到所有葉節點的路徑
    public static List<List<Integer>> rootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfsPaths(root, path, result);
        return result;
    }

    private static void dfsPaths(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;

        path.add(node.val);

        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            dfsPaths(node.left, path, result);
            dfsPaths(node.right, path, result);
        }

        path.remove(path.size() - 1);
    }

    // 2. 判斷是否存在和為目標值的根到葉路徑
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 3. 找出根到葉路徑的最大和
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;
        int leftMax = maxRootToLeafSum(root.left);
        int rightMax = maxRootToLeafSum(root.right);
        return root.val + Math.max(leftMax, rightMax);
    }

    // 4. 計算任意兩節點間最大路徑和（樹的直徑加權）
    private static int maxPath = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxPath = Integer.MIN_VALUE;
        maxGain(root);
        return maxPath;
    }

    private static int maxGain(TreeNode node) {
        if (node == null) return 0;

        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        int currentPathSum = node.val + leftGain + rightGain;

        maxPath = Math.max(maxPath, currentPathSum);

        return node.val + Math.max(leftGain, rightGain);
    }
}
