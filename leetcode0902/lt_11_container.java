public class lt_11_container {

    /**
     * 計算最大容器能裝的水量
     * @param height 每條直線的高度陣列
     * @return 最大面積
     */
    public static int maxArea(int[] height) {
        int left = 0;                  // 左指標
        int right = height.length - 1; // 右指標
        int maxArea = 0;               // 紀錄最大面積

        while (left < right) {
            // 目前容器高度 = 較短的那條線
            int h = Math.min(height[left], height[right]);
            // 目前容器寬度 = 兩指標距離
            int w = right - left;
            // 計算面積
            int area = h * w;
            // 更新最大值
            if (area > maxArea) {
                maxArea = area;
            }

            // 移動較短的一邊（因為面積受限於 min 高度）
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        int[] test1 = {1,8,6,2,5,4,8,3,7};
        int[] test2 = {1,1};
        int[] test3 = {4,3,2,1,4};

        System.out.println(maxArea(test1)); // 49
        System.out.println(maxArea(test2)); // 1
        System.out.println(maxArea(test3)); // 16
    }
}
