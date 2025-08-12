public class ValidMaxHeapChecker {

    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        if (n <= 1) return true; // 空陣列或單一元素一定是 Max Heap

        // 最後一個非葉節點的索引
        int lastParent = (n - 2) / 2;

        for (int i = 0; i <= lastParent; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[i] < arr[left]) {
                System.out.println("違反規則的索引: " + left + " (值 " + arr[left] + " 大於父節點 " + arr[i] + ")");
                return false;
            }
            if (right < n && arr[i] < arr[right]) {
                System.out.println("違反規則的索引: " + right + " (值 " + arr[right] + " 大於父節點 " + arr[i] + ")");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };

        for (int[] arr : testCases) {
            System.out.print("陣列: ");
            printArray(arr);
            boolean result = isValidMaxHeap(arr);
            System.out.println("是否為 Max Heap? " + result);
            System.out.println("-------------------");
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
