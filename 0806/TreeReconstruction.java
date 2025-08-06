import java.util.*;

public class TreeReconstruction {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        // 範例走訪序列
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        int[] levelorder = {3,9,20,15,7};

        // 1. 前序 + 中序重建
        TreeNode root1 = buildTreePreIn(preorder, inorder);
        System.out.println("前序+中序重建 (inorder列印):");
        inorderPrint(root1);
        System.out.println();

        // 2. 後序 + 中序重建
        TreeNode root2 = buildTreePostIn(postorder, inorder);
        System.out.println("後序+中序重建 (inorder列印):");
        inorderPrint(root2);
        System.out.println();

        // 3. 層序重建完全二元樹
        TreeNode root3 = buildCompleteTreeByLevelOrder(levelorder);
        System.out.println("層序重建完全二元樹 (levelorder列印):");
        levelOrderPrint(root3);
        System.out.println();

        // 4. 驗證重建樹與原序列是否一致
        System.out.println("前序重建是否正確: " + Arrays.equals(preorder, preorderTraversal(root1)));
        System.out.println("後序重建是否正確: " + Arrays.equals(postorder, postorderTraversal(root2)));
    }

    // 1. 前序 + 中序重建
    static int preIndex = 0;
    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        preIndex = 0;
        return buildPreInHelper(preorder, inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildPreInHelper(int[] preorder, int[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd) return null;

        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        int inIndex = inStart;
        while (inIndex <= inEnd && inorder[inIndex] != rootVal) {
            inIndex++;
        }

        root.left = buildPreInHelper(preorder, inorder, inStart, inIndex - 1);
        root.right = buildPreInHelper(preorder, inorder, inIndex + 1, inEnd);
        return root;
    }

    // 2. 後序 + 中序重建
    static int postIndex;
    public static TreeNode buildTreePostIn(int[] postorder, int[] inorder) {
        postIndex = postorder.length - 1;
        return buildPostInHelper(postorder, inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildPostInHelper(int[] postorder, int[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd) return null;

        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        int inIndex = inEnd;
        while (inIndex >= inStart && inorder[inIndex] != rootVal) {
            inIndex--;
        }

        root.right = buildPostInHelper(postorder, inorder, inIndex + 1, inEnd);
        root.left = buildPostInHelper(postorder, inorder, inStart, inIndex - 1);
        return root;
    }

    // 3. 根據層序走訪結果重建完全二元樹
    public static TreeNode buildCompleteTreeByLevelOrder(int[] levelorder) {
        if (levelorder.length == 0) return null;
        TreeNode root = new TreeNode(levelorder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (i < levelorder.length) {
            TreeNode current = queue.poll();
            if (i < levelorder.length) {
                current.left = new TreeNode(levelorder[i++]);
                queue.offer(current.left);
            }
            if (i < levelorder.length) {
                current.right = new TreeNode(levelorder[i++]);
                queue.offer(current.right);
            }
        }
        return root;
    }

    // 驗證用：中序列印
    public static void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    // 驗證用：層序列印
    public static void levelOrderPrint(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            System.out.print(node.val + " ");
            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
    }

    // 驗證重建是否正確：前序遍歷
    public static int[] preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorderHelper(root, res);
        return res.stream().mapToInt(i -> i).toArray();
    }

    private static void preorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);
        preorderHelper(node.left, res);
        preorderHelper(node.right, res);
    }

    // 驗證重建是否正確：後序遍歷
    public static int[] postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderHelper(root, res);
        return res.stream().mapToInt(i -> i).toArray();
    }

    private static void postorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;
        postorderHelper(node.left, res);
        postorderHelper(node.right, res);
        res.add(node.val);
    }
}
