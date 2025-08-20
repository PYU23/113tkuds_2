import java.util.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        String[] times = new String[n];

        for (int i = 0; i < n; i++) {
            times[i] = sc.nextLine().trim();
        }
        String query = sc.nextLine().trim();

        // 把時間轉成分鐘方便比較
        int[] minutes = new int[n];
        for (int i = 0; i < n; i++) {
            minutes[i] = toMinutes(times[i]);
        }
        int q = toMinutes(query);

        // 二分搜尋：找第一個大於 q 的時間
        int ansIdx = -1;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (minutes[mid] > q) {
                ansIdx = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (ansIdx == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(times[ansIdx]);
        }
    }

    // 轉換 HH:mm → 總分鐘
    private static int toMinutes(String t) {
        String[] parts = t.split(":");
        int hh = Integer.parseInt(parts[0]);
        int mm = Integer.parseInt(parts[1]);
        return hh * 60 + mm;
    }
}
