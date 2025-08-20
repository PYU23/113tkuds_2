import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        Item(String n, int q) {
            name = n;
            qty = q;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            items.add(new Item(name, qty));
        }

        // 依銷量高到低排序，若銷量相同則依品名字典序
        Collections.sort(items, (a, b) -> {
            if (b.qty != a.qty) return b.qty - a.qty;
            return a.name.compareTo(b.name);
        });

        // 輸出 Top-K
        int limit = Math.min(K, n);
        for (int i = 0; i < limit; i++) {
            System.out.println(items.get(i).name + " " + items.get(i).qty);
        }
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：
 * 1. 讀入資料 O(n)。
 * 2. 排序需要 O(n log n)。
 * 3. 輸出前 K 筆 O(K)，但 K ≤ 50 << n，因此可忽略。
 * 總時間複雜度為 O(n log n)，在 n ≤ 1000 時效能足夠。
 */
