import java.util.Arrays;

public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] array = {64, 25, 12, 22, 11};

        System.out.println("原始陣列: " + Arrays.toString(array));

        int[] selectionSortedArray = array.clone();
        System.out.println("\n=== 選擇排序過程 ===");
        selectionSort(selectionSortedArray);

        int[] bubbleSortedArray = array.clone();
        System.out.println("\n=== 氣泡排序過程 ===");
        bubbleSort(bubbleSortedArray);
    }

    // 選擇排序實作
    public static void selectionSort(int[] array) {
        int n = array.length;
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                // 交換
                int temp = array[i];
                array[i] = array[minIdx];
                array[minIdx] = temp;
                swaps++;
            }

            System.out.println("第 " + (i + 1) + " 輪排序結果: " + Arrays.toString(array));
        }

        System.out.println("選擇排序完成！");
        System.out.println("總比較次數：" + comparisons);
        System.out.println("總交換次數：" + swaps);
    }

    // 氣泡排序實作
    public static void bubbleSort(int[] array) {
        int n = array.length;
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                comparisons++;
                if (array[j] > array[j + 1]) {
                    // 交換
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            System.out.println("第 " + (i + 1) + " 輪排序結果: " + Arrays.toString(array));

            if (!swapped) {
                break; // 已排序完成
            }
        }

        System.out.println("氣泡排序完成！");
        System.out.println("總比較次數：" + comparisons);
        System.out.println("總交換次數：" + swaps);
    }
}

