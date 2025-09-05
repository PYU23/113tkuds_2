import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        double[] A = new double[n];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) A[i] = sc.nextDouble();
        for (int j = 0; j < m; j++) B[j] = sc.nextDouble();
        sc.close();

        double ans = findMedianSortedArrays(A, B);

        // 輸出保留 1 位小數
        System.out.printf("%.1f\n", ans);
    }

    public static double findMedianSortedArrays(double[] A, double[] B) {
        // 確保 A 是較短的陣列
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A);
        }

        int n = A.length, m = B.length;
        int totalLeft = (n + m + 1) / 2; // 左半邊的數量

        int left = 0, right = n;
        while (left <= right) {
            int i = (left + right) / 2;       // A 左半邊長度
            int j = totalLeft - i;           // B 左半邊長度

            double Aleft  = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft  = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            // 檢查切割是否正確
            if (Aleft <= Bright && Bleft <= Aright) {
                if ((n + m) % 2 == 1) {
                    return Math.max(Aleft, Bleft);
                } else {
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
            } else if (Aleft > Bright) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }

        // 理論上不會到這裡
        throw new IllegalArgumentException("Input arrays not sorted or invalid.");
    }
}
