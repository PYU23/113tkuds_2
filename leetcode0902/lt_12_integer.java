// 檔名：lt_12_integer.java

public class lt_12_integer {

    // 題目 1：Two Sum
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; // 沒找到回傳 -1
    }

    // 題目 2：myAtoi - 字串轉 32-bit 整數
    public static int myAtoi(String s) {
        int i = 0, n = s.length(), sign = 1, result = 0;
        while (i < n && s.charAt(i) == ' ') i++; // 去除空白
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i++) == '-') ? -1 : 1;
        }
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i++) - '0';
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
        }
        return result * sign;
    }

    // 題目 3：Palindrome Number
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int original = x, reversed = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            if (reversed > (Integer.MAX_VALUE - digit) / 10) return false; // 溢位
            reversed = reversed * 10 + digit;
        }
        return original == reversed;
    }

    // 題目 4：Regular Expression Matching (支援 '.' 和 '*')
    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int j = 2; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    dp[i][j] = dp[i][j - 2] || ((p.charAt(j - 2) == '.' || p.charAt(j - 2) == sc) && dp[i - 1][j]);
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    // 測試 main
    public static void main(String[] args) {
        // Two Sum 測試
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] res = twoSum(nums, target);
        System.out.println("Two Sum: [" + res[0] + ", " + res[1] + "]");

        // myAtoi 測試
        System.out.println("myAtoi(\"   -42\"): " + myAtoi("   -42"));

        // Palindrome 測試
        System.out.println("isPalindrome(121): " + isPalindrome(121));
        System.out.println("isPalindrome(-121): " + isPalindrome(-121));

        // Regex 測試
        System.out.println("isMatch(\"aab\", \"c*a*b\"): " + isMatch("aab", "c*a*b"));
        System.out.println("isMatch(\"mississippi\", \"mis*is*p*.\"): " + isMatch("mississippi", "mis*is*p*."));
    }
}
