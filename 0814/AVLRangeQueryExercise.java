// AVLRangeQueryExercise.java
import java.util.*;

public class AVLRangeQueryExercise {

    static class AVLNode {
        int val;
        AVLNode left, right;
        int height;

        AVLNode(int val) {
            this.val = val;
            this.height = 1;
        }
    }

    static class AVLTree {
        AVLNode root;

        // 插入節點
        public void insert(int val) {
            root = insertRec(root, val);
        }

        private AVLNode insertRec(AVLNode node, int val) {
            if (node == null) return new AVLNode(val);

            if (val < node.val) node.left = insertRec(node.left, val);
            else if (val > node.val) node.right = insertRec(node.right, val);
            else return node;

            node.height = 1 + Math.max(height(node.left), height(node.right));
            return rebalance(node, val);
        }

        private int height(AVLNode node) {
            return node == null ? 0 : node.height;
        }

        private int getBalance(AVLNode node) {
            return node == null ? 0 : height(node.left) - height(node.right);
        }

        // 左右旋轉操作
        private AVLNode rightRotate(AVLNode y) {
            AVLNode x = y.left;
            AVLNode T2 = x.right;
            x.right = y;
            y.left = T2;
            y.height = 1 + Math.max(height(y.left), height(y.right));
            x.height = 1 + Math.max(height(x.left), height(x.right));
            return x;
        }

        private AVLNode leftRotate(AVLNode x) {
            AVLNode y = x.right;
            AVLNode T2 = y.left;
            y.left = x;
            x.right = T2;
            x.height = 1 + Math.max(height(x.left), height(x.right));
            y.height = 1 + Math.max(height(y.left), height(y.right));
            return y;
        }

        private AVLNode rebalance(AVLNode node, int val) {
            int balance = getBalance(node);

            // 左左
            if (balance > 1 && val < node.left.val) return rightRotate(node);
            // 右右
            if (balance < -1 && val > node.right.val) return leftRotate(node);
            // 左右
            if (balance > 1 && val > node.left.val) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            // 右左
            if (balance < -1 && val < node.right.val) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            return node;
        }

        // 範圍查詢
        public List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            rangeQueryRec(root, min, max, result);
            return result;
        }

        private void rangeQueryRec(AVLNode node, int min, int max, List<Integer> result) {
            if (node == null) return;

            // 利用 BST 性質剪枝
            if (node.val > min) rangeQueryRec(node.left, min, max, result);
            if (node.val >= min && node.val <= max) result.add(node.val);
            if (node.val < max) rangeQueryRec(node.right, min, max, result);
        }

        // 中序遍歷印出
        public void inorderPrint() {
            inorderRec(root);
            System.out.println();
        }

        private void inorderRec(AVLNode node) {
            if (node == null) return;
            inorderRec(node.left);
            System.out.print(node.val + " ");
            inorderRec(node.right);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        for (int v : values) tree.insert(v);

        tree.inorderPrint(); // 印出 AVL 節點

        System.out.println("範圍查詢 [12, 28]: " + tree.rangeQuery(12, 28));
        System.out.println("範圍查詢 [0, 15]: " + tree.rangeQuery(0, 15));
        System.out.println("範圍查詢 [30, 40]: " + tree.rangeQuery(30, 40));
    }
}
