class AVLNode {
    int data;
    AVLNode left, right;
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

public class AVLTree {
    private AVLNode root;

    private int getHeight(AVLNode node) {
        return (node != null) ? node.height : 0;
    }

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private AVLNode insertNode(AVLNode node, int data) {
        if (node == null) return new AVLNode(data);

        if (data < node.data) {
            node.left = insertNode(node.left, data);
        } else if (data > node.data) {
            node.right = insertNode(node.right, data);
        } else return node;

        node.updateHeight();
        int balance = node.getBalance();

        if (balance > 1 && data < node.left.data) return AVLRotations.rightRotate(node);
        if (balance < -1 && data > node.right.data) return AVLRotations.leftRotate(node);
        if (balance > 1 && data > node.left.data) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }
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

    private AVLNode findMin(AVLNode node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private AVLNode deleteNode(AVLNode node, int data) {
        if (node == null) return null;

        if (data < node.data) {
            node.left = deleteNode(node.left, data);
        } else if (data > node.data) {
            node.right = deleteNode(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                return (node.left != null) ? node.left : node.right;
            }
            AVLNode temp = findMin(node.right);
            node.data = temp.data;
            node.right = deleteNode(node.right, temp.data);
        }

        node.updateHeight();
        int balance = node.getBalance();

        if (balance > 1 && getBalanceSafe(node.left) >= 0) return AVLRotations.rightRotate(node);
        if (balance > 1 && getBalanceSafe(node.left) < 0) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }
        if (balance < -1 && getBalanceSafe(node.right) <= 0) return AVLRotations.leftRotate(node);
        if (balance < -1 && getBalanceSafe(node.right) > 0) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }

        return node;
    }

    private int getBalanceSafe(AVLNode node) {
        return (node != null) ? node.getBalance() : 0;
    }

    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    private int checkAVL(AVLNode node) {
        if (node == null) return 0;
        int leftHeight = checkAVL(node.left);
        int rightHeight = checkAVL(node.right);
        if (leftHeight == -1 || rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void printTree() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            printInOrder(node.right);
        }
    }

    // 測試 main
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        System.out.println("=== 插入測試 ===");
        int[] values = {10, 20, 30, 40, 50, 25};
        for (int v : values) {
            tree.insert(v);
            System.out.print("插入 " + v + " 後: ");
            tree.printTree();
        }

        System.out.println("\n搜尋 25: " + tree.search(25));
        System.out.println("搜尋 35: " + tree.search(35));

        System.out.println("\n=== 刪除測試 ===");
        tree.delete(40);
        System.out.print("刪除 40 後: ");
        tree.printTree();

        System.out.println("\nAVL 驗證: " + (tree.isValidAVL() ? "平衡" : "不平衡"));
    }
}
