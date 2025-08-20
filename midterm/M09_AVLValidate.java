import java.util.*;

public class M09_AVLValidate {
    // 樹節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        TreeNode root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (!isAVL(root).isAVL) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    // 建樹（層序，-1 表 null）
    private static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode node = q.poll();
            // 左子
            if (i < arr.length && arr[i] != -1) {
                node.left = new TreeNode(arr[i]);
                q.offer(node.left);
            }
            i++;
            // 右子
            if (i < arr.length && arr[i] != -1) {
                node.right = new TreeNode(arr[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }

    // 檢查 BST
    private static boolean isBST(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return isBST(root.left, min, root.val) &&
               isBST(root.right, root.val, max);
    }

    // AVL 檢查結果封裝
    static class AVLResult {
        int height;
        boolean isAVL;
        AVLResult(int h, boolean b) { height = h; isAVL = b; }
    }

    // 檢查 AVL（後序遞迴）
    private static AVLResult isAVL(TreeNode root) {
        if (root == null) return new AVLResult(0, true);

        AVLResult left = isAVL(root.left);
        AVLResult right = isAVL(root.right);

        int height = Math.max(left.height, right.height) + 1;
        boolean balance = Math.abs(left.height - right.height) <= 1;
        boolean validAVL = left.isAVL && right.isAVL && balance;

        return new AVLResult(height, validAVL);
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. 建樹層序掃描每個節點一次 → O(n)。
 * 2. BST 驗證遞迴走訪每個節點一次 → O(n)。
 * 3. AVL 驗證後序走訪每個節點一次 → O(n)。
 * 總時間複雜度 = O(n)，適用於 n ≤ 10^4。
 * Space Complexity: O(h)
 * 說明：
 * 遞迴堆疊最大深度為樹高 h，最壞 O(n)，平均平衡樹 O(log n)。
 */
