public class lt_26_remove {

    // 移除排序陣列中的重複元素
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int writeIndex = 1; // 下一個要寫入唯一元素的位置

        for (int readIndex = 1; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != nums[readIndex - 1]) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }

        return writeIndex; // 唯一元素數量
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
            {1,1,2},
            {0,0,1,1,1,2,2,3,3,4},
            {},
            {1,2,3,4,5},
            {2,2,2,2}
        };

        for (int[] test : tests) {
            System.out.print("Input: ");
            printArray(test, test.length);

            int k = removeDuplicates(test);

            System.out.println("Number of unique elements: " + k);
            System.out.print("Modified array: ");
            printArray(test, k);
            System.out.println("----------");
        }
    }
}
