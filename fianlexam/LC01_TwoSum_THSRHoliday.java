import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入 n 與 target
        int n = sc.nextInt();
        long target = sc.nextLong();

        long[] seats = new long[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextLong();
        }

        // HashMap: key = 需要的數，value = 對應的索引
        Map<Long, Integer> map = new HashMap<>();

        int idx1 = -1, idx2 = -1;
        for (int i = 0; i < n; i++) {
            long x = seats[i];

            // 檢查 map 裡是否有 x (代表有人在等這個數字)
            if (map.containsKey(x)) {
                idx1 = map.get(x);
                idx2 = i;
                break;
            }

            // 記錄「還需要 target-x」
            long need = target - x;
            map.put(need, i);
        }

        if (idx1 == -1) {
            System.out.println("-1 -1");
        } else {
            System.out.println(idx1 + " " + idx2);
        }

        sc.close();
    }
}
