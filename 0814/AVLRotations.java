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
}

public class AVLRotations {
    // 右旋轉
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // 旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.updateHeight();
        x.updateHeight();

        return x;
    }

    // 左旋轉
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // 旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.updateHeight();
        y.updateHeight();

        return y;
    }

    // 簡單印出樹的結構（右子樹在上，左子樹在下）
    public static void printTree(AVLNode node, String indent, boolean isRight) {
        if (node == null) return;

        printTree(node.right, indent + (isRight ? "        " : " |      "), true);
        System.out.println(indent + (isRight ? " /" : " \\") + "----- " + node.data);
        printTree(node.left, indent + (isRight ? " |      " : "        "), false);
    }

    public static void main(String[] args) {
        // 測試右旋
        AVLNode root = new AVLNode(3);
        root.left = new AVLNode(2);
        root.left.left = new AVLNode(1);

        root.updateHeight();
        root.left.updateHeight();

        System.out.println("=== 原始樹 (右旋前) ===");
        printTree(root, "", true);

        root = rightRotate(root);
        System.out.println("=== 右旋後 ===");
        printTree(root, "", true);

        // 測試左旋
        AVLNode root2 = new AVLNode(1);
        root2.right = new AVLNode(2);
        root2.right.right = new AVLNode(3);

        root2.updateHeight();
        root2.right.updateHeight();

        System.out.println("=== 原始樹 (左旋前) ===");
        printTree(root2, "", true);

        root2 = leftRotate(root2);
        System.out.println("=== 左旋後 ===");
        printTree(root2, "", true);
    }
}

