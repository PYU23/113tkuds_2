import java.util.*;
public class BSTRangeQuerySystem {
    // BST 節點類別
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        /*
        建立測試用BST：
                15
               /  \
              10   20
             / \   / \
            8  12 17 25
        */
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(10);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(12);
        root.right.left = new TreeNode(17);
        root.right.right = new TreeNode(25);

        int min = 10, max = 20;
        System.out.println("範圍 [" + min + ", " + max + "] 內的節點值:");
        List<Integer> nodesInRange = rangeQuery(root, min, max);
        System.out.println(nodesInRange);

        int count = rangeCount(root, min, max);
        System.out.println("範圍內節點數量: " + count);

        int sum = rangeSum(root, min, max);
        System.out.println("範圍內節點值總和: " + sum);

        int target = 16;
        int closest = findClosest(root, target);
        System.out.println("最接近 " + target + " 的節點值: " + closest);
    }

    // 1. 範圍查詢: 找出 [min, max] 範圍內所有節點值（中序遞迴）
    public static List<Integer> rangeQuery(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private static void rangeQueryHelper(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) return;
        if (node.val > min) {
            rangeQueryHelper(node.left, min, max, result);
        }
        if (node.val >= min && node.val <= max) {
            result.add(node.val);
        }
        if (node.val < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }

    // 2. 範圍計數: 計算範圍內節點數量
    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) {
            return rangeCount(root.right, min, max);
        } else if (root.val > max) {
            return rangeCount(root.left, min, max);
        } else {
            return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
        }
    }

    // 3. 範圍總和: 計算範圍內節點值總和
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) {
            return rangeSum(root.right, min, max);
        } else if (root.val > max) {
            return rangeSum(root.left, min, max);
        } else {
            return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
        }
    }

    // 4. 最接近查詢: 找出最接近 target 的節點值
    public static int findClosest(TreeNode root, int target) {
        TreeNode current = root;
        int closest = root.val;
        while (current != null) {
            if (Math.abs(current.val - target) < Math.abs(closest - target)) {
                closest = current.val;
            }
            if (target < current.val) {
                current = current.left;
            } else if (target > current.val) {
                current = current.right;
            } else {
                return current.val; // exact match
            }
        }
        return closest;
    }
}
