import java.util.*;

public class BinaryTreeBasicOperations {
    // 樹節點類別
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        /*
         建立一棵測試用的二元樹
                  10
                 /  \
                5    15
               / \     \
              3   7     20
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(20);

        // 1. 計算總和與平均值
        int sum = sumNodes(root);
        int count = countNodes(root);
        double average = (count == 0) ? 0 : (double) sum / count;
        System.out.println("節點總和: " + sum);
        System.out.println("節點平均值: " + average);

        // 2. 找最大值和最小值節點
        int maxVal = findMax(root);
        int minVal = findMin(root);
        System.out.println("最大節點值: " + maxVal);
        System.out.println("最小節點值: " + minVal);

        // 3. 計算樹的寬度（最大層節點數）
        int width = maxWidth(root);
        System.out.println("樹的最大寬度: " + width);

        // 4. 判斷是否為完全二元樹
        boolean isComplete = isCompleteBinaryTree(root);
        System.out.println("是否為完全二元樹: " + isComplete);
    }

    // 計算節點總和（遞迴）
    public static int sumNodes(TreeNode root) {
        if (root == null) return 0;
        return root.val + sumNodes(root.left) + sumNodes(root.right);
    }

    // 計算節點數量
    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // 找最大值節點（遞迴）
    public static int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
    }

    // 找最小值節點（遞迴）
    public static int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
    }

    // 計算樹的最大寬度（層序遍歷）
    public static int maxWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            maxWidth = Math.max(maxWidth, size);

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return maxWidth;
    }

    // 判斷是否為完全二元樹
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean encounteredNull = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                encounteredNull = true;
            } else {
                if (encounteredNull) {
                    // 遇到 null 後又遇到非 null 節點 => 非完全二元樹
                    return false;
                }
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return true;
    }
}
