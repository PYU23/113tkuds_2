import java.util.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        long l = (a / g) * b;  // 先除後乘，避免溢位

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    // 遞迴求最大公因數 (Euclidean Algorithm)
    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

/*
 * Time Complexity: O(log(min(a, b)))
 * 說明：
 * 1. 歐幾里得演算法的遞迴深度與每次取模有關。
 * 2. 在最壞情況（連續 Fibonacci 數）時，複雜度為 O(log(min(a, b)))。
 * 3. LCM 計算為 O(1)。
 * 總時間複雜度 = O(log(min(a, b)))，在 a, b ≤ 10^9 時非常快。
 */
