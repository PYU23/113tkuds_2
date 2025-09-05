import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();

        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        // 建立閉括號對應的開括號
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // 開括號：推入堆疊
            if (map.containsValue(c)) {
                stack.push(c);
            }
            // 閉括號：檢查棧頂
            else if (map.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false; // 提早返回
                }
            }
            // 題目保證只會有 ()[]{}，所以不需 else
        }

        // 最後若棧非空，代表有未匹配開括號
        return stack.isEmpty();
    }
}
