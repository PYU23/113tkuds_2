import java.util.*;

public class M01_BuildHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();   // max 或 min
        int n = sc.nextInt();
        int[] heap = new int[n];
        for (int i = 0; i < n; i++) {
            heap[i] = sc.nextInt();
        }
        
        // 建堆
        buildHeap(heap, n, type);

        // 輸出結果
        for (int i = 0; i < n; i++) {
            System.out.print(heap[i] + (i == n-1 ? "" : " "));
        }
    }

    // 建堆 (自底向上)
    public static void buildHeap(int[] heap, int n, String type) {
        // 最後一個非葉節點 = (n/2 - 1)
        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(heap, n, i, type);
        }
    }

    // 調整堆
    public static void heapify(int[] heap, int n, int i, String type) {
        int extreme = i; // maxHeap → 最大值, minHeap → 最小值
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (type.equals("max")) {
            if (left < n && heap[left] > heap[extreme]) extreme = left;
            if (right < n && heap[right] > heap[extreme]) extreme = right;
        } else { // min
            if (left < n && heap[left] < heap[extreme]) extreme = left;
            if (right < n && heap[right] < heap[extreme]) extreme = right;
        }

        if (extreme != i) {
            int temp = heap[i];
            heap[i] = heap[extreme];
            heap[extreme] = temp;
            heapify(heap, n, extreme, type);
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1.heapify(i) 的成本為 O(h)，h 為高度。
 * 2.高度為 h 的節點數大約是 n / 2^(h+1)，因此總成本為
 *    Σ (節點數 × 高度) ≈ Σ (n/2^(h+1) × h)。
 * 3.這個級數收斂為 O(n)，所以自底向上建堆的時間複雜度為 O(n)。
 */

