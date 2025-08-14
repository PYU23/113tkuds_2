import java.util.*;

class AVLNode {
    int data;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
    }

    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    public int getBalance() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }
}

class AVLRotations {
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }
}

class AVLTree {
    private AVLNode root;

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private AVLNode insertNode(AVLNode node, int data) {
        if (node == null) return new AVLNode(data);

        if (data < node.data) {
            node.left = insertNode(node.left, data);
        } else if (data > node.data) {
            node.right = insertNode(node.right, data);
        } else {
            return node; // 重複值不插入
        }

        node.updateHeight();
        int balance = node.getBalance();

        // LL
        if (balance > 1 && data < node.left.data) {
            return AVLRotations.rightRotate(node);
        }
        // RR
        if (balance < -1 && data > node.right.data) {
            return AVLRotations.leftRotate(node);
        }
        // LR
        if (balance > 1 && data > node.left.data) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }
        // RL
        if (balance < -1 && data < node.right.data) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }

        return node;
    }

    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(AVLNode node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        if (data < node.data) return searchNode(node.left, data);
        return searchNode(node.right, data);
    }
}

public class AVLPerformanceTest {
    public static void main(String[] args) {
        System.out.println("=== AVL 樹性能測試 ===");

        AVLTree avlTree = new AVLTree();
        Random random = new Random();
        int n = 10000;

        // 插入測試
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            avlTree.insert(random.nextInt(100000));
        }
        long insertTime = System.nanoTime() - startTime;

        // 搜尋測試
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            avlTree.search(random.nextInt(100000));
        }
        long searchTime = System.nanoTime() - startTime;

        System.out.println("插入 " + n + " 個元素耗時: " +
                insertTime / 1_000_000 + " ms");
        System.out.println("搜尋 1000 次耗時: " +
                searchTime / 1_000_000 + " ms");
        System.out.println("平均插入時間複雜度: O(log n)");
        System.out.println("平均搜尋時間複雜度: O(log n)");
    }
}
