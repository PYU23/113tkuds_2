import java.util.*;

public class RecursiveTreePreview {

    // 範例：模擬檔案系統的資料夾結構 (Map<String, Object>)
    // Object 是 Integer (檔案數量) 或 Map 代表子資料夾
    public static void main(String[] args) {
        // 模擬資料夾結構
        Map<String, Object> fileSystem = new HashMap<>();
        fileSystem.put("root", Map.of(
            "docs", Map.of(
                "file1.txt", 1,
                "file2.txt", 1
            ),
            "images", Map.of(
                "img1.jpg", 1,
                "vacation", Map.of(
                    "img2.jpg", 1,
                    "img3.jpg", 1
                )
            ),
            "readme.md", 1
        ));

        System.out.println("總檔案數: " + countFiles(fileSystem));

        System.out.println("\n=== 多層選單結構列印 ===");
        Map<String, Object> menu = Map.of(
            "主選單1", Map.of(
                "子選單1-1", Map.of(
                    "子選單1-1-1", "項目A",
                    "子選單1-1-2", "項目B"
                ),
                "子選單1-2", "項目C"
            ),
            "主選單2", "項目D"
        );
        printMenu(menu, 0);

        System.out.println("\n=== 巢狀陣列展平 ===");
        Object[] nestedArray = new Object[] {
            1,
            new Object[] {2, 3, new Object[] {4, 5}},
            6,
            new Object[] {7, new Object[] {8, 9}}
        };
        List<Integer> flatList = new ArrayList<>();
        flattenArray(nestedArray, flatList);
        System.out.println("展平後的陣列: " + flatList);

        System.out.println("\n=== 巢狀清單最大深度 ===");
        List<Object> nestedList = List.of(
            1,
            List.of(2, 3, List.of(4, 5)),
            6,
            List.of(7, List.of(8, 9))
        );
        int maxDepth = maxDepthList(nestedList);
        System.out.println("最大深度: " + maxDepth);
    }

    // 1. 遞迴計算資料夾的總檔案數 (Object是Integer檔案或Map子資料夾)
    public static int countFiles(Map<String, Object> folder) {
        int count = 0;
        for (Object value : folder.values()) {
            if (value instanceof Integer) {
                count += (Integer) value; // 檔案數量
            } else if (value instanceof Map) {
                // 子資料夾遞迴
                @SuppressWarnings("unchecked")
                Map<String, Object> subFolder = (Map<String, Object>) value;
                count += countFiles(subFolder);
            }
        }
        return count;
    }

    // 2. 遞迴列印多層選單結構 (Object可為String或Map)
    public static void printMenu(Map<String, Object> menu, int level) {
        String indent = "  ".repeat(level);
        for (Map.Entry<String, Object> entry : menu.entrySet()) {
            System.out.println(indent + entry.getKey());
            Object value = entry.getValue();
            if (value instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> subMenu = (Map<String, Object>) value;
                printMenu(subMenu, level + 1);
            } else if (value instanceof String) {
                System.out.println(indent + "  - " + value);
            }
        }
    }

    // 3. 遞迴處理巢狀陣列展平
    public static void flattenArray(Object[] arr, List<Integer> result) {
        for (Object elem : arr) {
            if (elem instanceof Integer) {
                result.add((Integer) elem);
            } else if (elem instanceof Object[]) {
                flattenArray((Object[]) elem, result);
            }
        }
    }

    // 4. 遞迴計算巢狀清單的最大深度
    public static int maxDepthList(List<Object> list) {
        int maxDepth = 1;
        for (Object elem : list) {
            if (elem instanceof List) {
                @SuppressWarnings("unchecked")
                List<Object> sublist = (List<Object>) elem;
                int depth = 1 + maxDepthList(sublist);
                if (depth > maxDepth) maxDepth = depth;
            }
        }
        return maxDepth;
    }
}
