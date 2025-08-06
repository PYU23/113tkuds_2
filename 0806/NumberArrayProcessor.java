import java.util.*;
public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 2, 3, 4, 4, 5};
        int[] array2 = {3, 4, 6, 7, 8};

        System.out.println("原始陣列 1: " + Arrays.toString(array1));
        System.out.println("原始陣列 2: " + Arrays.toString(array2));

        // 移除重複元素
        int[] uniqueArray = removeDuplicates(array1);
        System.out.println("移除重複後: " + Arrays.toString(uniqueArray));

        // 合併兩個已排序陣列
        int[] mergedArray = mergeSortedArrays(array1, array2);
        System.out.println("合併後的陣列: " + Arrays.toString(mergedArray));

        // 找出出現頻率最高的元素
        int mostFrequent = findMostFrequentElement(array1);
        System.out.println("陣列 1 中出現頻率最高的元素: " + mostFrequent);

        // 分割陣列
        System.out.println("將陣列 2 分割成兩個子陣列:");
        splitArray(array2);
    }
    // 移除重複元素
    public static int[] removeDuplicates(int[] array) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : array) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }
    // 合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = b[j++];
            }
        }
        while (i < a.length) {
            merged[k++] = a[i++];
        }
        while (j < b.length) {
            merged[k++] = b[j++];
        }
        return merged;
    }
    // 找出陣列中出現頻率最高的元素
    public static int findMostFrequentElement(int[] array) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : array) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int mostFrequent = array[0];
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }
    // 將陣列分割成兩個相等或近似相等的子陣列
    public static void splitArray(int[] array) {
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        System.out.println("左子陣列: " + Arrays.toString(left));
        System.out.println("右子陣列: " + Arrays.toString(right));
    }
}
