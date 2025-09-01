public class lt_09_palindromeint {
    static class Solution {
        public boolean isPalindrome(int x) {
            // 負數或末尾是0的非零數都不是回文
            if (x < 0 || (x % 10 == 0 && x != 0)) return false;

            int rev = 0;
            while (x > rev) {
                rev = rev * 10 + x % 10;
                x /= 10;
            }

            // 偶數位：x == rev，奇數位：x == rev/10
            return x == rev || x == rev / 10;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] testCases = {121, -121, 10, 12321, 0, 1, 1221, 1234321, 1001};

        for (int x : testCases) {
            System.out.println("Input: " + x + " -> isPalindrome: " + sol.isPalindrome(x));
        }
    }
}

