// 檔名：lt_38_count.java

class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String prev = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= prev.length(); i++) {
            if (i < prev.length() && prev.charAt(i) == prev.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(prev.charAt(i - 1));
                count = 1;
            }
        }
        return sb.toString();
    }

    // 測試用 main
    public static void main(String[] args) {
        Solution sol = new Solution();
        for (int i = 1; i <= 6; i++) {
            System.out.println("countAndSay(" + i + ") = " + sol.countAndSay(i));
        }
    }
}
