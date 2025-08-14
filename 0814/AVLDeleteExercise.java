// AVLDeleteExercise.java
public class AVLDeleteExercise {

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

        // 計算高度
        private int height(AVLNode node) {
            return node == null ? 0 : node.height;
        }

        // 更新高度
        private void updateHeight(AVLNode node) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }

        // 平衡因子
        private int getBalance(AVLNode node) {
            return node == null ? 0 : height(node.left) - height(node.right);
        }

        // 右旋
        private AVLNode rightRotate(AVLNode y) {
            AVLNode x = y.left;
            AVLNode T2 = x.right;

            x.right = y;
            y.left = T2;

            updateHeight(y);
            updateHeight(x);

            return x;
        }

        // 左旋
        private AVLNode leftRotate(AVLNode x) {
            AVLNode y = x.right;
            AVLNode T2 = y.left;

            y.left = x;
            x.right = T2;

            updateHeight(x);
            updateHeight(y);

            return y;
        }

        // 插入
        public void insert(int val) {
            root = insertRec(root, val);
        }

        private AVLNode insertRec(AVLNode node, int val) {
            if (node == null) return new AVLNode(val);

            if (val < node.val) node.left = insertRec(node.left, val);
            else if (val > node.val) node.right = insertRec(node.right, val);
            else return node;

            updateHeight(node);

            return rebalance(node, val);
        }

        // 重新平衡
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

        // 刪除
        public void delete(int val) {
            root = deleteRec(root, val);
        }

        private AVLNode deleteRec(AVLNode node, int val) {
            if (node == null) return null;

            // 刪除 BST 節點
            if (val < node.val) node.left = deleteRec(node.left, val);
            else if (val > node.val) node.right = deleteRec(node.right, val);
            else {
                // 節點有一個或零個子節點
                if (node.left == null || node.right == null) {
                    AVLNode temp = node.left != null ? node.left : node.right;
                    if (temp == null) { // 無子節點
                        node = null;
                    } else { // 一個子節點
                        node = temp;
                    }
                } else { // 兩個子節點
                    AVLNode successor = minValueNode(node.right);
                    node.val = successor.val;
                    node.right = deleteRec(node.right, successor.val);
                }
            }

            if (node == null) return null;

            // 更新高度
            updateHeight(node);

            // 重新平衡
            int balance = getBalance(node);

            // 左左
            if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);
            // 左右
            if (balance > 1 && getBalance(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            // 右右
            if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);
            // 右左
            if (balance < -1 && getBalance(node.right) > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        private AVLNode minValueNode(AVLNode node) {
            AVLNode current = node;
            while (current.left != null) current = current.left;
            return current;
        }

        // 中序遍歷
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
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) tree.insert(v);

        System.out.println("初始樹:");
        tree.inorderPrint();

        System.out.println("刪除葉子節點 20:");
        tree.delete(20);
        tree.inorderPrint();

        System.out.println("刪除只有一個子節點的節點 30:");
        tree.delete(30);
        tree.inorderPrint();

        System.out.println("刪除有兩個子節點的節點 50:");
        tree.delete(50);
        tree.inorderPrint();
    }
}
