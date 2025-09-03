public class lt_29_divide {

    // 不使用 *, /, % 進行整數除法
    public static int divide(int dividend, int divisor) {
        // 特殊情況：溢位
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (dividend == Integer.MIN_VALUE && divisor == 1) return Integer.MIN_VALUE;

        // 判斷符號
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 轉成 long 避免溢位
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);

        int result = 0;
        while (dvd >= dvs) {
            long temp = dvs;
            int multiple = 1;
            // 快速減法，倍增 divisor
            while (dvd >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            dvd -= temp;
            result += multiple;
        }

        return negative ? -result : result;
    }

    // 本地測試 main
    public static void main(String[] args) {
        int[][] tests = {
            {10, 3},
            {7, -3},
            {0, 1},
            {1, 1},
            {-2147483648, -1},
            {-2147483648, 2},
            {2147483647, 2}
        };

        for (int[] test : tests) {
            int dividend = test[0];
            int divisor = test[1];
            System.out.println("dividend = " + dividend + ", divisor = " + divisor);
            int quotient = divide(dividend, divisor);
            System.out.println("Output: " + quotient);
            System.out.println("----------");
        }
    }
}
