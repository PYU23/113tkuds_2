// 檔名：LC32_LongestValidParen_Metro.java

import java.util.*;

public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();

        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 棧底基準
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else { // c == ')'
                stack.pop();
                if (stack.isEmpty()) {
                    // 當前')'沒有匹配 → 重新設定基準
                    stack.push(i);
                } else {
                    // 當前合法長度
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}
