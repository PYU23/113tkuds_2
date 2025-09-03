// 檔名：lt_27_remove_element.java
public class lt_27_remove_element {

    // 移除陣列中所有等於 val 的元素
    public static int removeElement(int[] nums, int val) {
        if (nums == null) return 0;

        int writeIndex = 0; // 下一個要寫入不等於 val 的位置

        for (int readIndex = 0; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != val) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }

        return writeIndex; // 不等於 val 的元素數量
    }

    // 印出陣列前 k 個元素
    public static void printArray(int[] nums, int k) {
        System.out.print("[");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i]);
            if (i != k - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // 本地測試 main
    public static void main(String[] args) {
        int[][] tests = {
            {3,2,2,3},
            {0,1,2,2,3,0,4,2},
            {1,2,3,4},
            {},
            {2,2,2,2}
        };
        int[] vals = {3, 2, 5, 1, 2};

        for (int i = 0; i < tests.length; i++) {
            System.out.print("Input: ");
            printArray(tests[i], tests[i].length);
            System.out.println("val = " + vals[i]);

            int k = removeElement(tests[i], vals[i]);

            System.out.println("Number of elements not equal to val: " + k);
            System.out.print("Modified array: ");
            printArray(tests[i], k);
            System.out.println("----------");
        }
    }
}
