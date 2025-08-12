
public class BSTConversionAndBalance {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 雙向鏈結串列節點
    static class DoublyListNode {
        int val;
        DoublyListNode prev, next;
        DoublyListNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        /*
                範例BST:
                     5
                    / \
                   3   8
                  / \   \
                 2   4   10
        */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(10);

        System.out.println("BST轉雙向鏈結串列:");
        DoublyListNode head = bstToDoublyList(root);
        printDoublyList(head);

        System.out.println("\n排序陣列轉平衡BST:");
        int[] sortedArr = {1,2,3,4,5,6,7};
        TreeNode balancedRoot = sortedArrayToBST(sortedArr);
        inorderPrint(balancedRoot);
        System.out.println();

        System.out.println("BST是否平衡? " + isBalanced(root));
        System.out.println("計算平衡因子 (節點值:因子):");
        printBalanceFactors(root);

        System.out.println("\nBST節點值轉換為大於等於該節點值的總和:");
        convertToGreaterSumTree(root);
        inorderPrint(root);
        System.out.println();
    }

    // 1. 將BST轉為排序的雙向鏈結串列（中序遍歷）
    static DoublyListNode head = null, prev = null;
    public static DoublyListNode bstToDoublyList(TreeNode root) {
        head = null;
        prev = null;
        bstToDoublyListHelper(root);
        return head;
    }

    private static void bstToDoublyListHelper(TreeNode node) {
        if (node == null) return;
        bstToDoublyListHelper(node.left);

        DoublyListNode curr = new DoublyListNode(node.val);
        if (prev == null) {
            head = curr;
        } else {
            prev.next = curr;
            curr.prev = prev;
        }
        prev = curr;

        bstToDoublyListHelper(node.right);
    }

    private static void printDoublyList(DoublyListNode head) {
        DoublyListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" <-> ");
            curr = curr.next;
        }
        System.out.println();
    }

    // 2. 將排序陣列轉為平衡BST
    public static TreeNode sortedArrayToBST(int[] arr) {
        return sortedArrayToBSTHelper(arr, 0, arr.length -1);
    }

    private static TreeNode sortedArrayToBSTHelper(int[] arr, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = sortedArrayToBSTHelper(arr, start, mid -1);
        node.right = sortedArrayToBSTHelper(arr, mid +1, end);
        return node;
    }

    // 3. 檢查BST是否平衡，並計算平衡因子
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    // 平衡因子 = 左子樹高度 - 右子樹高度
    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1;
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1;

        int balanceFactor = leftHeight - rightHeight;
        if (Math.abs(balanceFactor) > 1) return -1;
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // 計算並印出每個節點的平衡因子
    public static void printBalanceFactors(TreeNode root) {
        printBalanceFactorsHelper(root);
    }

    private static int printBalanceFactorsHelper(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = printBalanceFactorsHelper(node.left);
        int rightHeight = printBalanceFactorsHelper(node.right);
        int bf = leftHeight - rightHeight;
        System.out.println("節點 " + node.val + ": 平衡因子 " + bf);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // 4. 將BST中每個節點值改為大於等於該節點值的總和（逆序中序遍歷）
    static int sumSoFar = 0;
    public static void convertToGreaterSumTree(TreeNode root) {
        sumSoFar = 0;
        convertHelper(root);
    }

    private static void convertHelper(TreeNode node) {
        if (node == null) return;
        convertHelper(node.right);
        sumSoFar += node.val;
        node.val = sumSoFar;
        convertHelper(node.left);
    }

    // 輔助：中序列印
    public static void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }
}
