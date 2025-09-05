// 檔名：LC17_PhoneCombos_CSShift.java

import java.util.*;

public class LC17_PhoneCombos_CSShift {
    private static final String[] KEYS = {
        "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine().trim();
        sc.close();

        List<String> res = letterCombinations(digits);
        for (String s : res) {
            System.out.println(s);
        }
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        backtrack(res, new StringBuilder(), digits, 0);
        return res;
    }

    private static void backtrack(List<String> res, StringBuilder path, String digits, int idx) {
        if (idx == digits.length()) {
            res.add(path.toString());
            return;
        }
        String letters = KEYS[digits.charAt(idx) - '2'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(res, path, digits, idx + 1);
            path.deleteCharAt(path.length() - 1); // 回溯
        }
    }
}
