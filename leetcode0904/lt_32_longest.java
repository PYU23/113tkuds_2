// 檔名：lt_32_longest.java

class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        int[] dp = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] - 1 >= 0 
                           && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 +
                            ((i - dp[i - 1] - 2 >= 0) ? dp[i - dp[i - 1] - 2] : 0);
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }

        return maxLen;
    }

    // 測試用 main
    public static void main(String[] args) {
        Solution sol = new Solution();

        String s1 = "(()";
        System.out.println("Input: \"(()\" -> Output: " + sol.longestValidParentheses(s1)); 
        // 2

        String s2 = ")()())";
        System.out.println("Input: \")()())\" -> Output: " + sol.longestValidParentheses(s2)); 
        // 4

        String s3 = "";
        System.out.println("Input: \"\" -> Output: " + sol.longestValidParentheses(s3)); 
        // 0

        String s4 = "()(())";
        System.out.println("Input: \"()(())\" -> Output: " + sol.longestValidParentheses(s4)); 
        // 6
    }
}
