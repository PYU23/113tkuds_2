import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();

        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        // Map<字元, 該字元最後一次出現的索引>
        Map<Character, Integer> lastSeen = new HashMap<>();
        int left = 0, ans = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 若字元曾出現過，移動左指針
            if (lastSeen.containsKey(c)) {
                left = Math.max(left, lastSeen.get(c) + 1);
            }

            // 更新字元最後位置
            lastSeen.put(c, right);

            // 更新最大長度
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
