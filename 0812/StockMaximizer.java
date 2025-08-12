import java.util.*;

public class StockMaximizer {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k == 0) {
            return 0;
        }

        // 如果 K 大到足以做無限制交易，就直接用簡單貪心法
        if (k >= prices.length / 2) {
            return quickSolve(prices);
        }

        // 儲存每筆獲利的 Max Heap
        PriorityQueue<Integer> profits = new PriorityQueue<>(Collections.reverseOrder());

        int i = 0;
        while (i < prices.length - 1) {
            // 找到谷底
            while (i < prices.length - 1 && prices[i + 1] <= prices[i]) {
                i++;
            }
            int buy = prices[i];

            // 找到峰頂
            while (i < prices.length - 1 && prices[i + 1] > prices[i]) {
                i++;
            }
            int sell = prices[i];

            // 計算這筆交易利潤
            if (sell > buy) {
                profits.offer(sell - buy);
            }
        }

        // 從 Heap 中取前 K 筆最大利潤
        int totalProfit = 0;
        for (int t = 0; t < k && !profits.isEmpty(); t++) {
            totalProfit += profits.poll();
        }

        return totalProfit;
    }

    // 當 K 足夠多時，直接累加所有漲幅
    private int quickSolve(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        StockMaximizer sm = new StockMaximizer();

        int[] prices1 = {2, 4, 1};
        System.out.println(sm.maxProfit(2, prices1)); // 預期 2

        int[] prices2 = {3, 2, 6, 5, 0, 3};
        System.out.println(sm.maxProfit(2, prices2)); // 預期 7

        int[] prices3 = {1, 2, 3, 4, 5};
        System.out.println(sm.maxProfit(2, prices3)); // 預期 4
    }
}
