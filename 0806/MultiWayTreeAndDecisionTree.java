import java.util.*;
public class MultiWayTreeAndDecisionTree {
    // 多路樹節點 (任意多子節點)
    static class MultiWayTreeNode {
        String val;
        List<MultiWayTreeNode> children;

        MultiWayTreeNode(String v) {
            val = v;
            children = new ArrayList<>();
        }

        void addChild(MultiWayTreeNode child) {
            children.add(child);
        }
    }

    public static void main(String[] args) {
        // 範例：建立多路樹
        MultiWayTreeNode root = new MultiWayTreeNode("根");
        MultiWayTreeNode c1 = new MultiWayTreeNode("子1");
        MultiWayTreeNode c2 = new MultiWayTreeNode("子2");
        MultiWayTreeNode c3 = new MultiWayTreeNode("子3");
        root.addChild(c1);
        root.addChild(c2);
        root.addChild(c3);

        c1.addChild(new MultiWayTreeNode("子1-1"));
        c1.addChild(new MultiWayTreeNode("子1-2"));

        c2.addChild(new MultiWayTreeNode("子2-1"));

        System.out.println("多路樹深度優先走訪：");
        dfs(root);

        System.out.println("\n多路樹廣度優先走訪：");
        bfs(root);

        System.out.println("\n多路樹高度：" + treeHeight(root));

        System.out.println("\n每個節點的度數：");
        printDegree(root);

        // 模擬簡單決策樹：猜數字遊戲
        System.out.println("\n簡單猜數字決策樹示範:");
        MultiWayTreeNode decisionRoot = buildSimpleGuessDecisionTree();
        simulateDecisionTree(decisionRoot);
    }

    // 多路樹深度優先走訪
    static void dfs(MultiWayTreeNode node) {
        if (node == null) return;
        System.out.println(node.val);
        for (MultiWayTreeNode child : node.children) {
            dfs(child);
        }
    }

    // 多路樹廣度優先走訪
    static void bfs(MultiWayTreeNode root) {
        if (root == null) return;
        Queue<MultiWayTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            MultiWayTreeNode node = queue.poll();
            System.out.println(node.val);
            for (MultiWayTreeNode child : node.children) {
                queue.offer(child);
            }
        }
    }

    // 多路樹高度 (子樹最高深度+1)
    static int treeHeight(MultiWayTreeNode node) {
        if (node == null) return 0;
        int maxChildHeight = 0;
        for (MultiWayTreeNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, treeHeight(child));
        }
        return maxChildHeight + 1;
    }

    // 印出每個節點的度數（子節點數）
    static void printDegree(MultiWayTreeNode node) {
        if (node == null) return;
        System.out.println(node.val + " 度數: " + node.children.size());
        for (MultiWayTreeNode child : node.children) {
            printDegree(child);
        }
    }

    // 建立簡單的猜數字決策樹
    static MultiWayTreeNode buildSimpleGuessDecisionTree() {
        MultiWayTreeNode root = new MultiWayTreeNode("目標數字是10嗎？");
        MultiWayTreeNode yesNode = new MultiWayTreeNode("猜對了！");
        MultiWayTreeNode noNode = new MultiWayTreeNode("目標數字大於10嗎？");

        root.addChild(yesNode); // yes
        root.addChild(noNode);  // no

        MultiWayTreeNode noYes = new MultiWayTreeNode("目標數字是15嗎？");
        MultiWayTreeNode noNo = new MultiWayTreeNode("目標數字是5嗎？");

        noNode.addChild(noYes);  // yes
        noNode.addChild(noNo);   // no

        noYes.addChild(new MultiWayTreeNode("猜對了！"));
        noYes.addChild(new MultiWayTreeNode("猜錯了！"));

        noNo.addChild(new MultiWayTreeNode("猜對了！"));
        noNo.addChild(new MultiWayTreeNode("猜錯了！"));

        return root;
    }

    // 模擬決策樹遊戲 (簡易版，以硬編碼模擬輸入)
    static void simulateDecisionTree(MultiWayTreeNode root) {
        Scanner sc = new Scanner(System.in);
        MultiWayTreeNode current = root;

        while (true) {
            System.out.println(current.val + " (輸入 0: 是, 1: 否)");
            int ans = sc.nextInt();
            if (ans < 0 || ans > 1) {
                System.out.println("輸入錯誤，請輸入 0 或 1");
                continue;
            }

            if (current.children.isEmpty()) {
                System.out.println("遊戲結束");
                break;
            }
            current = current.children.get(ans);

            if (current.children.isEmpty()) {
                System.out.println(current.val);
                System.out.println("遊戲結束");
                break;
            }
        }
        sc.close();
    }
}
