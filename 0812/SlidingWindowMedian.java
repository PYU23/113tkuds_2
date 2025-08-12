import java.util.*;

public class SlidingWindowMedian {

    // 最大堆（存較小的一半數字）
    private PriorityQueue<Integer> maxHeap;
    // 最小堆（存較大的一半數字）
    private PriorityQueue<Integer> minHeap;
    // 延遲刪除 map
    private Map<Integer, Integer> delayed;

    private int maxHeapSize; // maxHeap 內有效元素數量
    private int minHeapSize; // minHeap 內有效元素數量

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();
        maxHeapSize = 0;
        minHeapSize = 0;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        int ri = 0;

        for (int i = 0; i < nums.length; i++) {
            addNum(nums[i]);

            if (i >= k) {
                removeNum(nums[i - k]);
            }

            if (i >= k - 1) {
                result[ri++] = getMedian(k);
            }
        }

        return result;
    }

    // 新增元素
    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
            maxHeapSize++;
        } else {
            minHeap.offer(num);
            minHeapSize++;
        }
        balanceHeaps();
    }

    // 延遲刪除元素
    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);

        if (num <= maxHeap.peek()) {
            maxHeapSize--;
            if (num == maxHeap.peek()) {
                prune(maxHeap);
            }
        } else {
            minHeapSize--;
            if (num == minHeap.peek()) {
                prune(minHeap);
            }
        }
        balanceHeaps();
    }

    // 取得中位數
    private double getMedian(int k) {
        if (k % 2 == 1) {
            return maxHeap.peek();
        } else {
            return ((long)maxHeap.peek() + (long)minHeap.peek()) / 2.0;
        }
    }

    // 平衡兩個 heap 的大小
    private void balanceHeaps() {
        if (maxHeapSize > minHeapSize + 1) {
            minHeap.offer(maxHeap.poll());
            maxHeapSize--;
            minHeapSize++;
            prune(maxHeap);
        } else if (maxHeapSize < minHeapSize) {
            maxHeap.offer(minHeap.poll());
            minHeapSize--;
            maxHeapSize++;
            prune(minHeap);
        }
    }

    // 移除延遲刪除的元素
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.peek();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
            heap.poll();
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println("輸入: " + Arrays.toString(nums1) + ", K=3");
        System.out.println("輸出: " + Arrays.toString(swm.medianSlidingWindow(nums1, 3)));
        // 預期: [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

        int[] nums2 = {1, 2, 3, 4};
        System.out.println("輸入: " + Arrays.toString(nums2) + ", K=2");
        System.out.println("輸出: " + Arrays.toString(swm.medianSlidingWindow(nums2, 2)));
        // 預期: [1.5, 2.5, 3.5]
    }
}
