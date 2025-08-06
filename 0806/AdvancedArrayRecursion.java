import java.util.*;
public class AdvancedArrayRecursion {
    public static void main(String[] args) {
        int[] array = {9, 3, 7, 5, 6, 2, 8};
        System.out.println("原始陣列: " + Arrays.toString(array));

        // 遞迴快速排序
        quickSort(array, 0, array.length - 1);
        System.out.println("快速排序後: " + Arrays.toString(array));

        // 遞迴合併兩個已排序陣列
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8, 9};
        int[] merged = mergeArrays(arr1, arr2, 0, 0, new ArrayList<>());
        System.out.println("遞迴合併後的陣列: " + merged);

        // 遞迴尋找第 k 小元素 (Quickselect)
        int[] arrayForKth = {7, 10, 4, 3, 20, 15};
        int k = 3;
        int kthSmallest = quickSelect(arrayForKth, 0, arrayForKth.length - 1, k - 1);
        System.out.println("第 " + k + " 小的元素是: " + kthSmallest);

        // 遞迴檢查是否存在子序列總和等於目標值
        int[] subsetArray = {3, 34, 4, 12, 5, 2};
        int targetSum = 9;
        boolean exists = subsetSum(subsetArray, subsetArray.length, targetSum);
        System.out.println("是否存在子序列總和為 " + targetSum + " : " + exists);
    }

    // 遞迴實作快速排序
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 遞迴合併兩個已排序陣列
    public static int[] mergeArrays(int[] a, int[] b, int i, int j, List<Integer> result) {
        if (i == a.length && j == b.length) {
            return result.stream().mapToInt(x -> x).toArray();
        }
        if (i == a.length) {
            result.add(b[j]);
            return mergeArrays(a, b, i, j + 1, result);
        }
        if (j == b.length) {
            result.add(a[i]);
            return mergeArrays(a, b, i + 1, j, result);
        }
        if (a[i] < b[j]) {
            result.add(a[i]);
            return mergeArrays(a, b, i + 1, j, result);
        } else {
            result.add(b[j]);
            return mergeArrays(a, b, i, j + 1, result);
        }
    }

    // 遞迴尋找第 k 小元素 (QuickSelect)
    public static int quickSelect(int[] arr, int low, int high, int k) {
        if (low == high) {
            return arr[low];
        }
        int pi = partition(arr, low, high);

        if (pi == k) {
            return arr[pi];
        } else if (pi > k) {
            return quickSelect(arr, low, pi - 1, k);
        } else {
            return quickSelect(arr, pi + 1, high, k);
        }
    }

    // 遞迴檢查是否存在子序列總和等於目標值 (Subset Sum Problem)
    public static boolean subsetSum(int[] arr, int n, int sum) {
        if (sum == 0) {
            return true;
        }
        if (n == 0) {
            return false;
        }
        if (arr[n - 1] > sum) {
            return subsetSum(arr, n - 1, sum);
        }
        return subsetSum(arr, n - 1, sum) || subsetSum(arr, n - 1, sum - arr[n - 1]);
    }
}

