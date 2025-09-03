public class lt_28_strstr {

    // 找到 needle 在 haystack 中的第一個位置
    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        if (haystack == null || haystack.length() < needle.length()) return -1;

        int n = haystack.length();
        int m = needle.length();

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == m) return i;
        }

        return -1;
    }

    // 本地測試 main
    public static void main(String[] args) {
        String[][] tests = {
            {"sadbutsad", "sad"},
            {"leetcode", "leeto"},
            {"hello", "ll"},
            {"aaaaa", "bba"},
            {"", ""},
            {"a", "a"},
            {"a", "b"}
        };

        for (String[] test : tests) {
            String haystack = test[0];
            String needle = test[1];
            System.out.println("haystack = \"" + haystack + "\", needle = \"" + needle + "\"");
            int index = strStr(haystack, needle);
            System.out.println("Output: " + index);
            System.out.println("----------");
        }
    }
}
