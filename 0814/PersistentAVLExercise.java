// PersistentAVLExercise.java
import java.util.*;

public class PersistentAVLExercise {

    // 不可變節點
    static class Node {
        final int val;
        final Node left, right;
        final int height;

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(height(left), height(right));
        }

        static int height(Node node) {
            return node == null ? 0 : node.height;
        }

        int balance() {
            return height(left) - height(right);
        }
    }

    static class PersistentAVL {
        List<Node> versions = new ArrayList<>();

        // 建立初始空版本
        public PersistentAVL() {
            versions.add(null);
        }

        public Node getVersion(int version) {
            if (version < 0 || version >= versions.size()) return null;
            return versions.get(version);
        }

        // 插入產生新版本
        public void insert(int version, int val) {
            Node newRoot = insertRec(getVersion(version), val);
            versions.add(newRoot);
        }

        private Node insertRec(Node node, int val) {
            if (node == null) return new Node(val, null, null);

            Node newNode;
            if (val < node.val) {
                newNode = new Node(node.val, insertRec(node.left, val), node.right);
            } else if (val > node.val) {
                newNode = new Node(node.val, node.left, insertRec(node.right, val));
            } else {
                return node; // 不插入重複值
            }

            return rebalance(newNode);
        }

        // 旋轉操作
        private Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;
            return new Node(x.val, x.left, new Node(y.val, T2, y.right));
        }

        private Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;
            return new Node(y.val, new Node(x.val, x.left, T2), y.right);
        }

        // 重新平衡
        private Node rebalance(Node node) {
            int balance = node.balance();

            // 左左
            if (balance > 1 && node.left.balance() >= 0) return rightRotate(node);
            // 左右
            if (balance > 1 && node.left.balance() < 0) {
                Node newLeft = leftRotate(node.left);
                return rightRotate(new Node(node.val, newLeft, node.right));
            }
            // 右右
            if (balance < -1 && node.right.balance() <= 0) return leftRotate(node);
            // 右左
            if (balance < -1 && node.right.balance() > 0) {
                Node newRight = rightRotate(node.right);
                return leftRotate(new Node(node.val, node.left, newRight));
            }

            return node; // 已平衡
        }

        // 中序遍歷
        public List<Integer> inorder(Node node) {
            List<Integer> result = new ArrayList<>();
            inorderRec(node, result);
            return result;
        }

        private void inorderRec(Node node, List<Integer> result) {
            if (node == null) return;
            inorderRec(node.left, result);
            result.add(node.val);
            inorderRec(node.right, result);
        }
    }

    // 測試
    public static void main(String[] args) {
        PersistentAVL tree = new PersistentAVL();

        // 版本 0: 空樹
        tree.insert(0, 10); // 版本 1
        tree.insert(1, 20); // 版本 2
        tree.insert(2, 5);  // 版本 3
        tree.insert(3, 15); // 版本 4

        // 列印各版本中序
        for (int i = 0; i <= 4; i++) {
            System.out.println("版本 " + i + ": " + tree.inorder(tree.getVersion(i)));
        }
    }
}
