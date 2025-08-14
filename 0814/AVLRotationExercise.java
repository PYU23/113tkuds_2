// AVLRotationExercise.java
public class AVLRotationExercise {

    static class AVLNode {
        int val;
        AVLNode left, right;
        int height;

        AVLNode(int val) {
            this.val = val;
            this.height = 1; // 新節點高度為 1
        }
    }

    static class AVLTree {
        AVLNode root;

        // 計算節點高度
        private int height(AVLNode node) {
            return node == null ? 0 : node.height;
        }

        // 計算平衡因子
        private int getBalance(AVLNode node) {
            return node == null ? 0 : height(node.left) - height(node.right);
        }

        // 更新節點高度
        private void updateHeight(AVLNode node) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }

        // 左旋
        private AVLNode leftRotate(AVLNode x) {
            AVLNode y = x.right;
            AVLNode T2 = y.left;

            // 旋轉
            y.left = x;
            x.right = T2;

            // 更新高度
            updateHeight(x);
            updateHeight(y);

            return y;
        }

        // 右旋
        private AVLNode rightRotate(AVLNode y) {
            AVLNode x = y.left;
            AVLNode T2 = x.right;

            // 旋轉
            x.right = y;
            y.left = T2;

            // 更新高度
            updateHeight(y);
            updateHeight(x);

            return x;
        }

        // 左右旋：先左旋再右旋
        private AVLNode leftRightRotate(AVLNode node) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 右左旋：先右旋再左旋
        private AVLNode rightLeftRotate(AVLNode node) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // 插入節點並保持平衡
        public AVLNode insert(AVLNode node, int val) {
            if (node == null) return new AVLNode(val);

            if (val < node.val) node.left = insert(node.left, val);
            else if (val > node.val) node.right = insert(node.right, val);
            else return node; // 不插入重複值

            // 更新高度
            updateHeight(node);

            int balance = getBalance(node);

            // 左左不平衡
            if (balance > 1 && val < node.left.val) return rightRotate(node);
            // 右右不平衡
            if (balance < -1 && val > node.right.val) return leftRotate(node);
            // 左右不平衡
            if (balance > 1 && val > node.left.val) return leftRightRotate(node);
            // 右左不平衡
            if (balance < -1 && val < node.right.val) return rightLeftRotate(node);

            return node; // 已平衡
        }

        public void insert(int val) {
            root = insert(root, val);
        }

        // 中序遍歷列印
        public void inorderPrint() {
            inorderRec(root);
            System.out.println();
        }

        private void inorderRec(AVLNode node) {
            if (node == null) return;
            inorderRec(node.left);
            System.out.print(node.val + "(" + node.height + ") ");
            inorderRec(node.right);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // 測試各種不平衡情況
        int[] testValues = {30, 20, 10, 25, 40, 50, 45};
        for (int v : testValues) tree.insert(v);

        tree.inorderPrint(); // 印出節點與高度
    }
}

