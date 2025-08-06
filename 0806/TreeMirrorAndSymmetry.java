public class TreeMirrorAndSymmetry {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }
    public static void main(String[] args) {
        /*
         樹1:
                 1
               /   \
              2     2
             /       \
            3         3

         樹2 (鏡像樹1):
                 1
               /   \
              2     2
               \   /
                3 3
        */
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(2);
        tree1.left.left = new TreeNode(3);
        tree1.right.right = new TreeNode(3);

        TreeNode tree2 = new TreeNode(1);
        tree2.left = new TreeNode(2);
        tree2.right = new TreeNode(2);
        tree2.left.right = new TreeNode(3);
        tree2.right.left = new TreeNode(3);

        System.out.println("樹1是否對稱？ " + isSymmetric(tree1));

        System.out.println("\n鏡像樹1 (inorder列印):");
        mirror(tree1);
        inorderPrint(tree1);
        System.out.println();

        System.out.println("\n樹1與樹2是否互為鏡像？ " + areMirrors(tree1, tree2));

        System.out.println("\n樹2是否為樹1的子樹？ " + isSubtree(tree1, tree2));
    }

    // 1. 判斷是否為對稱樹
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    // 2. 將樹轉換成鏡像樹（原地修改）
    public static void mirror(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirror(root.left);
        mirror(root.right);
    }

    // 中序列印樹節點
    public static void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    // 3. 比較兩棵樹是否互為鏡像
    public static boolean areMirrors(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        return areMirrors(t1.left, t2.right) && areMirrors(t1.right, t2.left);
    }

    // 4. 判斷 t2 是否為 t1 的子樹
    public static boolean isSubtree(TreeNode t1, TreeNode t2) {
        if (t2 == null) return true;  // 空樹為任意樹的子樹
        if (t1 == null) return false;
        if (isSameTree(t1, t2)) return true;
        return isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
    }

    // 判斷兩棵樹是否相同
    public static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }
}
