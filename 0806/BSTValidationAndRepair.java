import java.util.*;

public class BSTValidationAndRepair {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 範例建立錯誤BST：
    // 本來BST： 1, 3, 4, 6, 8
    // 錯誤交換節點值 3 和 8
    public static TreeNode buildErrorBST() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(8); // 錯誤節點，應該是3
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.right.right = new TreeNode(3); // 錯誤節點，應該是8
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = buildErrorBST();

        System.out.println("是否為有效BST？ " + isValidBST(root));

        List<TreeNode> swappedNodes = findSwappedNodes(root);
        System.out.print("不符合規則的節點值：");
        for (TreeNode n : swappedNodes) System.out.print(n.val + " ");
        System.out.println();

        if (swappedNodes.size() == 2) {
            fixBST(root, swappedNodes.get(0), swappedNodes.get(1));
            System.out.println("修復後是否為有效BST？ " + isValidBST(root));
        }

        int removeCount = minRemoveToBST(root);
        System.out.println("最少移除節點數使樹成為BST（簡單估計）: " + removeCount);
    }

    // 1. 驗證是否為有效BST (中序遍歷遞增序列)
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private static boolean isValidBSTHelper(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if (min != null && node.val <= min) return false;
        if (max != null && node.val >= max) return false;
        return isValidBSTHelper(node.left, min, node.val) && isValidBSTHelper(node.right, node.val, max);
    }

    // 2. 找出不符合規則的兩個節點（交換節點）
    // 用中序遍歷找出兩個錯誤節點（逆序對）
    public static List<TreeNode> findSwappedNodes(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        TreeNode[] prev = new TreeNode[1];
        TreeNode[] first = new TreeNode[1];
        TreeNode[] second = new TreeNode[1];

        inorderFindSwapped(root, prev, first, second);
        if (first[0] != null && second[0] != null) {
            res.add(first[0]);
            res.add(second[0]);
        }
        return res;
    }

    private static void inorderFindSwapped(TreeNode node, TreeNode[] prev, TreeNode[] first, TreeNode[] second) {
        if (node == null) return;

        inorderFindSwapped(node.left, prev, first, second);

        if (prev[0] != null && prev[0].val > node.val) {
            if (first[0] == null) {
                first[0] = prev[0];
                second[0] = node;
            } else {
                second[0] = node;
            }
        }
        prev[0] = node;

        inorderFindSwapped(node.right, prev, first, second);
    }

    // 3. 修復BST (交換兩錯誤節點值)
    public static void fixBST(TreeNode root, TreeNode n1, TreeNode n2) {
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }

    // 4. 計算最少移除節點使樹成BST (簡單版：利用最大BST子樹大小)
    public static int minRemoveToBST(TreeNode root) {
        int totalNodes = countNodes(root);
        int maxBSTSubtreeSize = largestBSTSubtree(root).maxSize;
        return totalNodes - maxBSTSubtreeSize;
    }

    private static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // 利用輔助類別紀錄子樹資訊
    static class BSTInfo {
        boolean isBST;
        int minVal, maxVal;
        int maxSize;
        BSTInfo(boolean isBST, int minVal, int maxVal, int maxSize) {
            this.isBST = isBST;
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.maxSize = maxSize;
        }
    }

    // 找最大BST子樹大小
    private static BSTInfo largestBSTSubtree(TreeNode node) {
        if (node == null) {
            return new BSTInfo(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        BSTInfo left = largestBSTSubtree(node.left);
        BSTInfo right = largestBSTSubtree(node.right);

        if (left.isBST && right.isBST && node.val > left.maxVal && node.val < right.minVal) {
            int size = 1 + left.maxSize + right.maxSize;
            int minVal = Math.min(node.val, left.minVal);
            int maxVal = Math.max(node.val, right.maxVal);
            return new BSTInfo(true, minVal, maxVal, size);
        } else {
            return new BSTInfo(false, 0, 0, Math.max(left.maxSize, right.maxSize));
        }
    }
}
