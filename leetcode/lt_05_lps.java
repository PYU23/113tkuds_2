public class lt_05_lps {
    static class Solution {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) return "";

            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                int len1 = expandAroundCenter(s, i, i);
                int len2 = expandAroundCenter(s, i, i + 1);
                int len = Math.max(len1, len2);

                if (len > end - start) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        private int expandAroundCenter(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            return right - left - 1;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        String s1 = "babad";
        String s2 = "cbbd";
        String s3 = "a";
        String s4 = "ac";

        System.out.println("Input: " + s1 + " -> " + sol.longestPalindrome(s1)); // "bab" 或 "aba"
        System.out.println("Input: " + s2 + " -> " + sol.longestPalindrome(s2)); // "bb"
        System.out.println("Input: " + s3 + " -> " + sol.longestPalindrome(s3)); // "a"
        System.out.println("Input: " + s4 + " -> " + sol.longestPalindrome(s4)); // "a" 或 "c"
    }
}
