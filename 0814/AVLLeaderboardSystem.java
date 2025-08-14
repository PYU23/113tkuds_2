// AVLLeaderboardSystem.java
import java.util.*;

public class AVLLeaderboardSystem {

    static class PlayerNode {
        String player;
        int score;
        PlayerNode left, right;
        int height;
        int size; // 子樹大小

        PlayerNode(String player, int score) {
            this.player = player;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }
    }

    static class AVLLeaderboard {
        PlayerNode root;
        Map<String, PlayerNode> playerMap = new HashMap<>();

        private int height(PlayerNode node) {
            return node == null ? 0 : node.height;
        }

        private int size(PlayerNode node) {
            return node == null ? 0 : node.size;
        }

        private void update(PlayerNode node) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.size = 1 + size(node.left) + size(node.right);
        }

        private int getBalance(PlayerNode node) {
            return node == null ? 0 : height(node.left) - height(node.right);
        }

        private PlayerNode rightRotate(PlayerNode y) {
            PlayerNode x = y.left;
            PlayerNode T2 = x.right;

            x.right = y;
            y.left = T2;

            update(y);
            update(x);
            return x;
        }

        private PlayerNode leftRotate(PlayerNode x) {
            PlayerNode y = x.right;
            PlayerNode T2 = y.left;

            y.left = x;
            x.right = T2;

            update(x);
            update(y);
            return y;
        }

        private PlayerNode rebalance(PlayerNode node) {
            update(node);
            int balance = getBalance(node);

            if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);
            if (balance > 1 && getBalance(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);
            if (balance < -1 && getBalance(node.right) > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            return node;
        }

        // 插入或更新玩家分數
        public void addOrUpdatePlayer(String player, int score) {
            if (playerMap.containsKey(player)) {
                root = delete(root, playerMap.get(player).score, player);
            }
            root = insert(root, player, score);
        }

        private PlayerNode insert(PlayerNode node, String player, int score) {
            if (node == null) {
                PlayerNode newNode = new PlayerNode(player, score);
                playerMap.put(player, newNode);
                return newNode;
            }

            if (score > node.score || (score == node.score && player.compareTo(node.player) < 0)) {
                node.left = insert(node.left, player, score);
            } else {
                node.right = insert(node.right, player, score);
            }

            return rebalance(node);
        }

        // 刪除節點
        private PlayerNode delete(PlayerNode node, int score, String player) {
            if (node == null) return null;

            if (score > node.score || (score == node.score && player.compareTo(node.player) < 0)) {
                node.left = delete(node.left, score, player);
            } else if (score < node.score || (score == node.score && player.compareTo(node.player) > 0)) {
                node.right = delete(node.right, score, player);
            } else { // 找到節點
                if (node.left == null || node.right == null) {
                    PlayerNode temp = node.left != null ? node.left : node.right;
                    if (temp == null) {
                        playerMap.remove(node.player);
                        node = null;
                    } else {
                        playerMap.put(temp.player, temp);
                        node = temp;
                    }
                } else {
                    PlayerNode successor = minValueNode(node.right);
                    node.player = successor.player;
                    node.score = successor.score;
                    playerMap.put(node.player, node);
                    node.right = delete(node.right, successor.score, successor.player);
                }
            }

            if (node == null) return null;

            return rebalance(node);
        }

        private PlayerNode minValueNode(PlayerNode node) {
            PlayerNode current = node;
            while (current.left != null) current = current.left;
            return current;
        }

        // 查詢玩家排名 (1 為最高分)
        public int getRank(String player) {
            if (!playerMap.containsKey(player)) return -1;
            return 1 + getRankRec(root, playerMap.get(player).score, player);
        }

        private int getRankRec(PlayerNode node, int score, String player) {
            if (node == null) return 0;
            if (score > node.score || (score == node.score && player.compareTo(node.player) < 0)) {
                return getRankRec(node.left, score, player);
            } else if (score < node.score || (score == node.score && player.compareTo(node.player) > 0)) {
                return size(node.left) + 1 + getRankRec(node.right, score, player);
            } else {
                return size(node.left);
            }
        }

        // 查詢前 K 名玩家
        public List<String> topK(int k) {
            List<String> result = new ArrayList<>();
            topKRec(root, result, k);
            return result;
        }

        private void topKRec(PlayerNode node, List<String> result, int k) {
            if (node == null || result.size() >= k) return;
            topKRec(node.left, result, k);
            if (result.size() < k) result.add(node.player + "(" + node.score + ")");
            topKRec(node.right, result, k);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLLeaderboard leaderboard = new AVLLeaderboard();

        leaderboard.addOrUpdatePlayer("Alice", 95);
        leaderboard.addOrUpdatePlayer("Bob", 85);
        leaderboard.addOrUpdatePlayer("Charlie", 90);
        leaderboard.addOrUpdatePlayer("David", 80);
        leaderboard.addOrUpdatePlayer("Eve", 95); // 同分數測試

        System.out.println("前 3 名: " + leaderboard.topK(3));
        System.out.println("Charlie 排名: " + leaderboard.getRank("Charlie"));
        System.out.println("Bob 排名: " + leaderboard.getRank("Bob"));

        leaderboard.addOrUpdatePlayer("Bob", 98); // 更新分數
        System.out.println("更新 Bob 分數後前 3 名: " + leaderboard.topK(3));
        System.out.println("Bob 排名: " + leaderboard.getRank("Bob"));
    }
}

