import java.util.*;

public class LevelOrderTraversalVariations {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        /*
        範例樹：
                1
              /   \
             2     3
            / \   / \
           4   5 6   7
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("每層節點分別存在List中:");
        List<List<Integer>> levelLists = levelOrderByLevels(root);
        System.out.println(levelLists);

        System.out.println("\n之字形層序走訪:");
        List<List<Integer>> zigzagLists = zigzagLevelOrder(root);
        System.out.println(zigzagLists);

        System.out.println("\n每層最後一個節點:");
        List<Integer> lastNodes = lastNodeEachLevel(root);
        System.out.println(lastNodes);

        System.out.println("\n垂直層序走訪:");
        List<List<Integer>> verticalOrder = verticalOrderTraversal(root);
        System.out.println(verticalOrder);
    }

    // 1. 將每層節點存在不同List
    public static List<List<Integer>> levelOrderByLevels(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    // 2. 之字形層序走訪（zigzag）
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) level.addLast(node.val);
                else level.addFirst(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(level);
            leftToRight = !leftToRight;  // 換方向
        }
        return result;
    }

    // 3. 只列印每層的最後一個節點值
    public static List<Integer> lastNodeEachLevel(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int lastVal = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                lastVal = node.val; // 最後遍歷的節點就是最後一個節點

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(lastVal);
        }
        return result;
    }

    // 4. 垂直層序走訪（根節點水平位置為0，左-1，右+1）
    public static List<List<Integer>> verticalOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, List<Integer>> columnMap = new TreeMap<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            int col = pair.getValue();

            columnMap.putIfAbsent(col, new ArrayList<>());
            columnMap.get(col).add(node.val);

            if (node.left != null) queue.offer(new Pair<>(node.left, col - 1));
            if (node.right != null) queue.offer(new Pair<>(node.right, col + 1));
        }

        for (List<Integer> colList : columnMap.values()) {
            result.add(colList);
        }
        return result;
    }

    // 簡單的泛型Pair類別，Java 8 以下沒有內建的Pair
    static class Pair<K, V> {
        private K key;
        private V value;
        public Pair(K k, V v) { key = k; value = v; }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }
}
