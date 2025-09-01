import java.util.HashMap;

public class lt_03_lswrc {
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            HashMap<Character, Integer> map = new HashMap<>();
            int left = 0, maxLen = 0;

            for (int right = 0; right < s.length(); right++) {
                char c = s.charAt(right);

                if (map.containsKey(c) && map.get(c) >= left) {
                    left = map.get(c) + 1;
                }

                map.put(c, right);
                maxLen = Math.max(maxLen, right - left + 1);
            }
            return maxLen;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";

        System.out.println("Input: " + s1 + " -> " + sol.lengthOfLongestSubstring(s1)); // 3
        System.out.println("Input: " + s2 + " -> " + sol.lengthOfLongestSubstring(s2)); // 1
        System.out.println("Input: " + s3 + " -> " + sol.lengthOfLongestSubstring(s3)); // 3
    }
}

