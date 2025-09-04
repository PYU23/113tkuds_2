import java.util.*;

public class lt_30_substring {

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return result;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) return result;

        // 統計 words 出現次數
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // 遍歷每個可能的起始位置
        for (int i = 0; i <= s.length() - totalLen; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            for (; j < wordCount; j++) {
                int start = i + j * wordLen;
                String sub = s.substring(start, start + wordLen);
                if (!wordMap.containsKey(sub)) break;
                seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                if (seen.get(sub) > wordMap.get(sub)) break;
            }
            if (j == wordCount) result.add(i);
        }

        return result;
    }

    // 本地測試 main
    public static void main(String[] args) {
        String[][] sTests = {
            {"barfoothefoobarman"},
            {"wordgoodgoodgoodbestword"},
            {"barfoofoobarthefoobarman"},
            {"foobarfoobar"},
            {"aaaaaa"}
        };

        String[][] wordsTests = {
            {"foo","bar"},
            {"word","good","best","word"},
            {"bar","foo","the"},
            {"foo","bar"},
            {"aa","aa"}
        };

        for (int i = 0; i < sTests.length; i++) {
            String s = sTests[i][0];
            String[] words = wordsTests[i];
            System.out.println("s = \"" + s + "\", words = " + Arrays.toString(words));
            List<Integer> indices = findSubstring(s, words);
            System.out.println("Output indices: " + indices);
            System.out.println("----------");
        }
    }
}
