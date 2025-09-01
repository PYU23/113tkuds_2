public class lt_08_myatoi {
    static class Solution {
        public int myAtoi(String s) {
            if (s == null || s.length() == 0) return 0;

            int i = 0, n = s.length();
            // 1. 忽略前導空格
            while (i < n && s.charAt(i) == ' ') i++;

            // 2. 處理符號
            int sign = 1;
            if (i < n) {
                if (s.charAt(i) == '-') {
                    sign = -1;
                    i++;
                } else if (s.charAt(i) == '+') {
                    i++;
                }
            }

            // 3. 讀取數字
            int result = 0;
            while (i < n) {
                char c = s.charAt(i);
                if (c < '0' || c > '9') break;
                int digit = c - '0';

                // 4. 溢位檢查
                if (result > Integer.MAX_VALUE / 10 || 
                   (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                result = result * 10 + digit;
                i++;
            }

            return result * sign;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] testCases = {
            "42",
            "   -42",
            "4193 with words",
            "words and 987",
            "-91283472332",
            "+0 123",
            "0000000000012345678",
            "-000000000000001"
        };

        for (String s : testCases) {
            System.out.println("Input: \"" + s + "\" -> Output: " + sol.myAtoi(s));
        }
    }
}

