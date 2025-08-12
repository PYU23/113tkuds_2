import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElement {

    // 方法 1：Max Heap，維持大小為 K
    public static int kthSmallest_MaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 移除最大值
            }
        }
        return maxHeap.peek();
    }

    // 方法 2：Min Heap，取出 K 次
    public static int kthSmallest_MinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
        }
        int result = -1;
        for (int i = 0; i < k; i++) {
            result = minHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] testArrays = {
            {7, 10, 4, 3, 20, 15},
            {1},
            {3, 1, 4, 1, 5, 9, 2, 6}
        };
        int[] ks = {3, 1, 4};
        int[] expected = {7, 1, 3};

        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            int k = ks[i];
            System.out.print("陣列: ");
            printArray(arr);
            System.out.println("K = " + k);

            long start1 = System.nanoTime();
            int ans1 = kthSmallest_MaxHeap(arr, k);
            long end1 = System.nanoTime();

            long start2 = System.nanoTime();
            int ans2 = kthSmallest_MinHeap(arr, k);
            long end2 = System.nanoTime();

            System.out.println("方法 1 (Max Heap) → " + ans1 + "，耗時: " + (end1 - start1) + " ns");
            System.out.println("方法 2 (Min Heap) → " + ans2 + "，耗時: " + (end2 - start2) + " ns");
            System.out.println("期望結果: " + expected[i]);
            System.out.println("------------------------");
        }
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
