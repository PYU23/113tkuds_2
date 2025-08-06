import java.util.*;

public class RecursionVsIteration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 二項式係數 C(n, k)
        System.out.print("請輸入 n (計算二項式係數 C(n,k))：");
        int n = sc.nextInt();
        System.out.print("請輸入 k：");
        int k = sc.nextInt();
        long start, end;

        // 遞迴
        start = System.nanoTime();
        int recC = binomialCoefficientRecursive(n, k);
        end = System.nanoTime();
        System.out.println("遞迴計算 C(" + n + "," + k + ") = " + recC + "，耗時 (ns): " + (end - start));

        // 迭代 (動態規劃)
        start = System.nanoTime();
        int iterC = binomialCoefficientIterative(n, k);
        end = System.nanoTime();
        System.out.println("迭代計算 C(" + n + "," + k + ") = " + iterC + "，耗時 (ns): " + (end - start));

        // 陣列元素乘積
        System.out.print("\n請輸入陣列長度：");
        int length = sc.nextInt();
        int[] array = new int[length];
        System.out.println("請輸入陣列元素：");
        for (int i = 0; i < length; i++) {
            array[i] = sc.nextInt();
        }

        start = System.nanoTime();
        long recProduct = productRecursive(array, 0);
        end = System.nanoTime();
        System.out.println("遞迴陣列元素乘積：" + recProduct + "，耗時 (ns): " + (end - start));

        start = System.nanoTime();
        long iterProduct = productIterative(array);
        end = System.nanoTime();
        System.out.println("迭代陣列元素乘積：" + iterProduct + "，耗時 (ns): " + (end - start));

        // 計算字串中元音字母數量
        sc.nextLine(); // 清除換行
        System.out.print("\n請輸入字串 (計算元音字母數量)：");
        String str = sc.nextLine();

        start = System.nanoTime();
        int recVowels = countVowelsRecursive(str, 0);
        end = System.nanoTime();
        System.out.println("遞迴計算元音數：" + recVowels + "，耗時 (ns): " + (end - start));

        start = System.nanoTime();
        int iterVowels = countVowelsIterative(str);
        end = System.nanoTime();
        System.out.println("迭代計算元音數：" + iterVowels + "，耗時 (ns): " + (end - start));

        // 檢查括號是否配對正確
        System.out.print("\n請輸入包含括號的字串 (檢查括號配對)：");
        String brackets = sc.nextLine();

        start = System.nanoTime();
        boolean recMatch = isBalancedRecursive(brackets, 0, 0);
        end = System.nanoTime();
        System.out.println("遞迴括號配對結果：" + recMatch + "，耗時 (ns): " + (end - start));

        start = System.nanoTime();
        boolean iterMatch = isBalancedIterative(brackets);
        end = System.nanoTime();
        System.out.println("迭代括號配對結果：" + iterMatch + "，耗時 (ns): " + (end - start));

        sc.close();
    }

    // 1. 計算二項式係數 - 遞迴
    public static int binomialCoefficientRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialCoefficientRecursive(n - 1, k - 1) + binomialCoefficientRecursive(n - 1, k);
    }

    // 迭代版本 - 動態規劃
    public static int binomialCoefficientIterative(int n, int k) {
        int[][] C = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            int maxJ = Math.min(i, k);
            for (int j = 0; j <= maxJ; j++) {
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }
        return C[n][k];
    }

    // 2. 陣列所有元素乘積 - 遞迴
    public static long productRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    // 迭代
    public static long productIterative(int[] arr) {
        long prod = 1;
        for (int v : arr) {
            prod *= v;
        }
        return prod;
    }

    // 3. 計算字串中元音字母數量 - 遞迴
    public static int countVowelsRecursive(String s, int index) {
        if (index == s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(index));
        int count = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;
        return count + countVowelsRecursive(s, index + 1);
    }

    // 迭代
    public static int countVowelsIterative(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    // 4. 檢查括號是否配對正確 - 遞迴
    // 這裡只檢查 '(' 與 ')' 的配對，使用一個計數器記錄目前未閉合的 '(' 數量
    public static boolean isBalancedRecursive(String s, int index, int openCount) {
        if (openCount < 0) return false; // 遇到多餘閉括號
        if (index == s.length()) return openCount == 0;
        char c = s.charAt(index);
        if (c == '(') {
            return isBalancedRecursive(s, index + 1, openCount + 1);
        } else if (c == ')') {
            return isBalancedRecursive(s, index + 1, openCount - 1);
        } else {
            return isBalancedRecursive(s, index + 1, openCount);
        }
    }

    // 迭代版本，利用 stack 檢查括號配對
    public static boolean isBalancedIterative(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
