import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {
        int val;
        char color; // 'R' 或 'B'
        Node left, right;
        int index;  // 對應輸入序列索引
        Node(int v, char c, int idx) { val = v; color = c; index = idx; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] vals = new int[n];
        char[] colors = new char[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
            String c = sc.next();
            colors[i] = c.charAt(0);
        }

        Node root = buildTree(vals, colors);

        // 性質 1: 根節點為黑
        if (root != null && root.color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 性質 2 & 3
        String violation = checkRB(root);
        if (violation != null) {
            System.out.println(violation);
        } else {
            System.out.println("RB Valid");
        }
    }

    // 層序建樹
    private static Node buildTree(int[] vals, char[] colors) {
        int n = vals.length;
        if (n == 0 || vals[0] == -1) return null;

        Node root = new Node(vals[0], colors[0], 0);
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (!q.isEmpty() && i < n) {
            Node cur = q.poll();

            // 左子
            if (i < n && vals[i] != -1) {
                cur.left = new Node(vals[i], colors[i], i);
                q.offer(cur.left);
            }
            i++;
            // 右子
            if (i < n && vals[i] != -1) {
                cur.right = new Node(vals[i], colors[i], i);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    // 遞迴檢查紅黑樹性質
    private static String checkRB(Node node) {
        return checkNode(node).violation;
    }

    static class Result {
        boolean isValid;
        int blackHeight;
        String violation;
        Result(boolean valid, int bh, String v) {
            isValid = valid; blackHeight = bh; violation = v;
        }
    }

    private static Result checkNode(Node node) {
        if (node == null) return new Result(true, 1, null); // null 當黑

        // 性質 2: 紅紅相鄰
        if (node.color == 'R') {
            if ((node.left != null && node.left.color == 'R')) 
                return new Result(false, 0, "RedRedViolation at index " + node.left.index);
            if ((node.right != null && node.right.color == 'R')) 
                return new Result(false, 0, "RedRedViolation at index " + node.right.index);
        }

        Result left = checkNode(node.left);
        if (!left.isValid) return left;

        Result right = checkNode(node.right);
        if (!right.isValid) return right;

        // 性質 3: 黑高度一致
        if (left.blackHeight != right.blackHeight) {
            return new Result(false, 0, "BlackHeightMismatch");
        }

        int bh = left.blackHeight + (node.color == 'B' ? 1 : 0);
        return new Result(true, bh, null);
    }
}


