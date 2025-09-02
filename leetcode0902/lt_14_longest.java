class Solution {

    /**
     * 找出字串陣列的最長共同前綴
     * @param strs 字串陣列
     * @return 最長共同前綴字串
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // 以第一個字串為基準
        String prefix = strs[0];

        // 遍歷陣列其他字串
        for (int i = 1; i < strs.length; i++) {
            // 比對 prefix 與 strs[i]，找最長匹配
            while (strs[i].indexOf(prefix) != 0) {
                // 每次減少 prefix 的長度，直到匹配
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }

    // 測試 main
    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] test1 = {"flower","flow","flight"};
        String[] test2 = {"dog","racecar","car"};
        String[] test3 = {"interview","internet","internal"};

        System.out.println(sol.longestCommonPrefix(test1)); // "fl"
        System.out.println(sol.longestCommonPrefix(test2)); // ""
        System.out.println(sol.longestCommonPrefix(test3)); // "inte"
    }
}
