import java.util.*;

public class M11_HeapSortWithTie {

    static class Student {
        int score;
        int index; // 輸入順序
        Student(int s, int idx) { score = s; index = idx; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            arr[i] = new Student(s, i);
        }

        heapSort(arr);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score + (i == n-1 ? "\n" : " "));
        }
    }

    // 建堆 + 排序
    private static void heapSort(Student[] arr) {
        int n = arr.length;

        // 自底向上建最小堆 (Min-Heap)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 逐步將堆頂 (最小值) 移到末尾
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }

        // 目前為降序，需反轉成遞增
        reverse(arr);
    }

    private static void heapify(Student[] arr, int n, int i) {
        int smallest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && compare(arr[l], arr[smallest]) < 0) smallest = l;
        if (r < n && compare(arr[r], arr[smallest]) < 0) smallest = r;

        if (smallest != i) {
            swap(arr, i, smallest);
            heapify(arr, n, smallest);
        }
    }

    private static int compare(Student a, Student b) {
        if (a.score != b.score) return a.score - b.score; // 分數升序
        return a.index - b.index; // 平手規則
    }

    private static void swap(Student[] arr, int i, int j) {
        Student tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void reverse(Student[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            swap(arr, l, r);
            l++; r--;
        }
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：
 * 1. 自底向上建堆 Heapify 整體 O(n)。
 * 2. 每次取出堆頂並重新 heapify O(log n)，共 n 次 → O(n log n)。
 * 3. reverse 陣列 O(n)。
 * 總時間複雜度 = O(n log n)。
 *
 * Space Complexity: O(1)
 * 說明：
 * 使用原地陣列排序，不需要額外儲存空間，僅少量變數。
 */
