import java.util.Scanner;

public class RecursiveMathCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 組合數 C(n, k)
        System.out.print("請輸入組合數的 n：");
        int n = sc.nextInt();
        System.out.print("請輸入組合數的 k：");
        int k = sc.nextInt();
        System.out.println("C(" + n + ", " + k + ") = " + combination(n, k));

        // 卡塔蘭數 C(n)
        System.out.print("\n請輸入卡塔蘭數的 n：");
        int catalanN = sc.nextInt();
        System.out.println("卡塔蘭數 C(" + catalanN + ") = " + catalan(catalanN));

        // 漢諾塔移動步數 hanoi(n)
        System.out.print("\n請輸入漢諾塔圓盤數量 n：");
        int hanoiN = sc.nextInt();
        System.out.println("漢諾塔最少步數 hanoi(" + hanoiN + ") = " + hanoi(hanoiN));

        // 判斷回文數
        System.out.print("\n請輸入一個數字判斷是否為回文數：");
        int palindromeNum = sc.nextInt();
        if (isPalindrome(palindromeNum)) {
            System.out.println(palindromeNum + " 是回文數。");
        } else {
            System.out.println(palindromeNum + " 不是回文數。");
        }

        sc.close();
    }

    // 計算組合數 C(n, k)
    public static int combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 計算卡塔蘭數 C(n)
    public static int catalan(int n) {
        if (n == 0) {
            return 1;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // 計算漢諾塔步數 hanoi(n)
    public static int hanoi(int n) {
        if (n == 1) {
            return 1;
        }
        return 2 * hanoi(n - 1) + 1;
    }

    // 判斷數字是否為回文數
    public static boolean isPalindrome(int number) {
        return number == reverse(number);
    }

    // 輔助方法：反轉數字
    public static int reverse(int number) {
        return reverseHelper(number, 0);
    }

    private static int reverseHelper(int number, int reversed) {
        if (number == 0) {
            return reversed;
        }
        return reverseHelper(number / 10, reversed * 10 + number % 10);
    }
}

