import java.util.*;
public class MovingAverageStream {
    private int size;
    private long sum = 0;
    private Queue<Integer> window = new ArrayDeque<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 小的一半
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 大的一半

    private PriorityQueue<Integer> minValHeap = new PriorityQueue<>(); // 最小值
    private PriorityQueue<Integer> maxValHeap = new PriorityQueue<>(Collections.reverseOrder()); // 最大值

    private Map<Integer, Integer> delayed = new HashMap<>(); // lazy deletion

    public MovingAverageStream(int size) {
        this.size = size;
    }

    public double next(int val) {
        window.offer(val);
        sum += val;

        // 放入中位數的 heap
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }
        balanceMedianHeaps();

        // 放入極值 heap
        minValHeap.offer(val);
        maxValHeap.offer(val);

        // 移除舊元素
        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;
            removeFromHeaps(removed);
        }

        return sum * 1.0 / window.size();
    }

    public double getMedian() {
        cleanTop(maxHeap);
        cleanTop(minHeap);
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public int getMin() {
        cleanTop(minValHeap);
        return minValHeap.peek();
    }

    public int getMax() {
        cleanTop(maxValHeap);
        return maxValHeap.peek();
    }

    // 平衡中位數的兩個 heap
    private void balanceMedianHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    // 移除舊元素（lazy deletion）
    private void removeFromHeaps(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);

        if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {
            cleanTop(maxHeap);
        } else {
            cleanTop(minHeap);
        }
        cleanTop(minValHeap);
        cleanTop(maxValHeap);
        balanceMedianHeaps();
    }

    // 從 heap 移除所有已標記刪除的元素
    private void cleanTop(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0) {
            int val = heap.poll();
            delayed.put(val, delayed.get(val) - 1);
            if (delayed.get(val) == 0) {
                delayed.remove(val);
            }
        }
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));   // 1.0
        System.out.println(ma.next(10));  // 5.5
        System.out.println(ma.next(3));   // 4.666...
        System.out.println(ma.next(5));   // 6.0
        System.out.println(ma.getMedian()); // 5.0
        System.out.println(ma.getMin());    // 3
        System.out.println(ma.getMax());    // 10
    }
}
