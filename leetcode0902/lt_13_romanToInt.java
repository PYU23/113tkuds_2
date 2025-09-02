

class Solution {

    /**
     * 將羅馬數字轉換成整數
     * @param s 羅馬數字字串
     * @return 對應的整數
     */
    public int romanToInt(String s) {
        int total = 0;
        int prev = 0;

        // 從右到左掃描
        for (int i = s.length() - 1; i >= 0; i--) {
            int val = value(s.charAt(i));

            // 減法規則：
            // 如果當前數字比右邊小，代表要減去
            if (val < prev) {
                total -= val;
            } else {
                total += val;
            }

            prev = val; // 更新前一個值
        }

        return total;
    }

    // 輔助方法：取得單個羅馬符號的數值
    private int value(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    // 測試 main 方法
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.romanToInt("III"));      // 3
        System.out.println(sol.romanToInt("IV"));       // 4
        System.out.println(sol.romanToInt("IX"));       // 9
        System.out.println(sol.romanToInt("LVIII"));    // 58
        System.out.println(sol.romanToInt("MCMXCIV"));  // 1994
    }
}
