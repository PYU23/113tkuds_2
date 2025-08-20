import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] income = new long[n];
        for (int i = 0; i < n; i++) {
            income[i] = sc.nextLong();
        }

        long totalTax = 0;
        for (int i = 0; i < n; i++) {
            long tax = computeTax(income[i]);
            totalTax += tax;
            System.out.println("Tax: " + tax);
        }

        // 平均稅額（整數除法四捨五入）
        double avg = (double) totalTax / n;
        System.out.printf("Average: %.0f\n", avg);
    }

    // 計算單一收入的稅額（累進稅）
    private static long computeTax(long x) {
        long tax = 0;

        if (x <= 120000) {
            tax = Math.round(x * 0.05);
        } else if (x <= 500000) {
            tax = Math.round(120000 * 0.05 + (x - 120000) * 0.12);
        } else if (x <= 1000000) {
            tax = Math.round(120000 * 0.05
                           + (500000 - 120000) * 0.12
                           + (x - 500000) * 0.20);
        } else {
            tax = Math.round(120000 * 0.05
                           + (500000 - 120000) * 0.12
                           + (1000000 - 500000) * 0.20
                           + (x - 1000000) * 0.30);
        }

        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. 計算每筆收入稅額 computeTax(x) 需要 O(1)。
 * 2. 共有 n 筆收入，因此總成本為 O(n)。
 * 3. 輸出與平均計算亦為 O(n)，不影響總體複雜度。
 * 總時間複雜度為 O(n)，在 n ≤ 1000 時效能非常足夠。
 */
