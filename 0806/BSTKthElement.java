import java.util.*;

public class BSTKthElement {
    static class TreeNode {
        int val;
        TreeNode left, right;
        int size; // 包含此節點在內的子樹節點數，用於快速定位第 k 小元素

        TreeNode(int v) {
            val = v;
            size = 1;
        }
    }

    private TreeNode root;

    // 建構子
    public BSTKthElement() {
        root = null;
    }

    // 動態插入
    public void insert(int val) {
        root = insert(root, val);
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insert(node.left, val);
        else node.right = insert(node.right, val);

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    // 動態刪除
    public void delete(int val) {
        root = delete(root, val);
    }

    private TreeNode delete(TreeNode node, int val) {
        if (node == null) return null;

        if (val < node.val) node.left = delete(node.left, val);
        else if (val > node.val) node.right = delete(node.right, val);
        else {
            // 找到要刪除節點
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            else {
                // 找右子樹最小節點替代
                TreeNode minNode = findMinNode(node.right);
                node.val = minNode.val;
                node.right = delete(node.right, minNode.val);
            }
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private TreeNode findMinNode(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private int size(TreeNode node) {
        return node == null ? 0 : node.size;
    }

    // 找第 k 小元素 (1-based)
    public Integer kthSmallest(int k) {
        if (k <= 0 || k > size(root)) return null;
        return kthSmallest(root, k);
    }

    private int kthSmallest(TreeNode node, int k) {
        int leftSize = size(node.left);
        if (k <= leftSize) {
            return kthSmallest(node.left, k);
        } else if (k == leftSize + 1) {
            return node.val;
        } else {
            return kthSmallest(node.right, k - leftSize - 1);
        }
    }

    // 找第 k 大元素 = 找第 (n-k+1) 小元素
    public Integer kthLargest(int k) {
        int n = size(root);
        return kthSmallest(n - k + 1);
    }

    // 找第 k 小到第 j 小之間元素 (含 k, j)
    public List<Integer> kthRange(int k, int j) {
        List<Integer> result = new ArrayList<>();
        int n = size(root);
        if (k > j || k <= 0 || j > n) return result;

        inorderRange(root, k, j, new int[]{0}, result);
        return result;
    }

    // 中序遍歷，記錄序號，抓取指定範圍元素
    private void inorderRange(TreeNode node, int k, int j, int[] count, List<Integer> result) {
        if (node == null) return;
        inorderRange(node.left, k, j, count, result);
        count[0]++;
        if (count[0] >= k && count[0] <= j) {
            result.add(node.val);
        }
        if (count[0] > j) return;
        inorderRange(node.right, k, j, count, result);
    }

    // 測試主程式
    public static void main(String[] args) {
        BSTKthElement bst = new BSTKthElement();

        int[] vals = {20, 8, 22, 4, 12, 10, 14};
        for (int v : vals) bst.insert(v);

        System.out.println("第3小元素: " + bst.kthSmallest(3)); // 10
        System.out.println("第2大元素: " + bst.kthLargest(2));  // 20

        System.out.println("第3小到第5小元素: " + bst.kthRange(3, 5)); // [10, 12, 14]

        System.out.println("\n動態插入 13, 17");
        bst.insert(13);
        bst.insert(17);
        System.out.println("第3小到第7小元素: " + bst.kthRange(3, 7)); // [10,12,13,14,17]

        System.out.println("\n動態刪除 12");
        bst.delete(12);
        System.out.println("第3小到第6小元素: " + bst.kthRange(3, 6)); // [10,13,14,17]
    }
}
