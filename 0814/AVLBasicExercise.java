// AVLBasicExercise.java
public class AVLBasicExercise {

    static class AVLNode {
        int val;
        AVLNode left, right;

        AVLNode(int val) {
            this.val = val;
        }
    }

    static class AVLTree {
        AVLNode root;

        // 插入節點 (標準 BST 插入)
        public void insert(int val) {
            root = insertRec(root, val);
        }

        private AVLNode insertRec(AVLNode node, int val) {
            if (node == null) return new AVLNode(val);

            if (val < node.val) node.left = insertRec(node.left, val);
            else if (val > node.val) node.right = insertRec(node.right, val);
            // 如果值相同，不插入
            return node;
        }

        // 搜尋節點
        public boolean search(int val) {
            return searchRec(root, val);
        }

        private boolean searchRec(AVLNode node, int val) {
            if (node == null) return false;
            if (val == node.val) return true;
            else if (val < node.val) return searchRec(node.left, val);
            else return searchRec(node.right, val);
        }

        // 計算高度
        public int height() {
            return heightRec(root);
        }

        private int heightRec(AVLNode node) {
            if (node == null) return 0;
            int leftHeight = heightRec(node.left);
            int rightHeight = heightRec(node.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }

        // 檢查是否為有效的 AVL 樹
        public boolean isAVL() {
            return checkAVL(root).isAVL;
        }

        // 輔助類別回傳平衡資訊
        static class AVLCheckResult {
            boolean isAVL;
            int height;

            AVLCheckResult(boolean isAVL, int height) {
                this.isAVL = isAVL;
                this.height = height;
            }
        }

        private AVLCheckResult checkAVL(AVLNode node) {
            if (node == null) return new AVLCheckResult(true, 0);

            AVLCheckResult left = checkAVL(node.left);
            AVLCheckResult right = checkAVL(node.right);

            boolean balanced = left.isAVL && right.isAVL &&
                               Math.abs(left.height - right.height) <= 1;
            int height = Math.max(left.height, right.height) + 1;

            return new AVLCheckResult(balanced, height);
        }

        // 中序遍歷列印節點
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
        int[] values = {10, 20, 5, 3, 8, 15};

        for (int v : values) tree.insert(v);

        tree.inorderPrint(); // 印出排序後節點
        System.out.println("搜尋 15: " + tree.search(15)); // true
        System.out.println("搜尋 7: " + tree.search(7));   // false
        System.out.println("樹高度: " + tree.height());
        System.out.println("是否為 AVL: " + tree.isAVL());
    }
}
