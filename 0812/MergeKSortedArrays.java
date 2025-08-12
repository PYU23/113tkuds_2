import java.util.*;
public class MergeKSortedArrays {
    // 小幫手類別：儲存值、來源陣列索引、來源陣列中的元素索引
    static class Node {
        int value;
        int arrayIndex;
        int elementIndex;

        Node(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));

        // 1. 先把每個陣列的第一個元素放進 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Node(arrays[i][0], i, 0));
            }
        }

        // 2. 每次取出最小值，並放入該陣列的下一個元素
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            result.add(node.value);

            int nextIndex = node.elementIndex + 1;
            if (nextIndex < arrays[node.arrayIndex].length) {
                minHeap.offer(new Node(arrays[node.arrayIndex][nextIndex], node.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][][] testCases = {
            {{1,4,5}, {1,3,4}, {2,6}},
            {{1,2,3}, {4,5,6}, {7,8,9}},
            {{1}, {0}}
        };

        int[][] expected = {
            {1,1,2,3,4,4,5,6},
            {1,2,3,4,5,6,7,8,9},
            {0,1}
        };

        for (int t = 0; t < testCases.length; t++) {
            List<Integer> merged = mergeKSortedArrays(testCases[t]);
            System.out.println("輸入: " + Arrays.deepToString(testCases[t]));
            System.out.println("輸出: " + merged);
            System.out.println("期望: " + Arrays.toString(expected[t]));
            System.out.println("------------------------");
        }
    }
}

