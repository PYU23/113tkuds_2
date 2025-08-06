import java.util.*;

public class AdvancedStringRecursion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入一個字串進行遞迴處理：");
        String input = sc.nextLine();

        System.out.println("\n=== 所有排列組合 ===");
        permute("", input);

        System.out.println("\n=== 字串匹配 (遞迴) ===");
        System.out.print("請輸入要搜尋的子字串：");
        String pattern = sc.nextLine();
        boolean found = stringMatch(input, pattern, 0);
        System.out.println("是否找到子字串：" + found);

        System.out.println("\n=== 移除重複字元後的結果 ===");
        String noDup = removeDuplicates(input, 0, new HashSet<>(), "");
        System.out.println(noDup);

        System.out.println("\n=== 所有子字串組合 ===");
        substringCombinations(input, 0, "");
        
        sc.close();
    }

    // 遞迴產生字串的所有排列組合
    public static void permute(String prefix, String str) {
        if (str.length() == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            permute(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
        }
    }

    // 遞迴實作字串匹配（類似 naive pattern matching）
    public static boolean stringMatch(String text, String pattern, int index) {
        if (index + pattern.length() > text.length()) {
            return false;
        }
        if (text.substring(index, index + pattern.length()).equals(pattern)) {
            return true;
        }
        return stringMatch(text, pattern, index + 1);
    }

    // 遞迴移除字串中的重複字符
    public static String removeDuplicates(String str, int index, Set<Character> seen, String result) {
        if (index == str.length()) {
            return result;
        }
        char current = str.charAt(index);
        if (!seen.contains(current)) {
            seen.add(current);
            result += current;
        }
        return removeDuplicates(str, index + 1, seen, result);
    }

    // 遞迴計算字串的所有子字串組合（Power Set）
    public static void substringCombinations(String str, int index, String current) {
        if (index == str.length()) {
            System.out.println(current);
            return;
        }
        // Include current character
        substringCombinations(str, index + 1, current + str.charAt(index));
        // Exclude current character
        substringCombinations(str, index + 1, current);
    }
}
