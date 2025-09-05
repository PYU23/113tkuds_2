import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }
        sc.close();

        System.out.println(maxArea(heights));
    }

    public static long maxArea(int[] heights) {
        int left = 0, right = heights.length - 1;
        long max = 0;

        while (left < right) {
            long width = right - left;
            long h = Math.min(heights[left], heights[right]);
            max = Math.max(max, width * h);

            // 移動短邊，才有可能變大
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
