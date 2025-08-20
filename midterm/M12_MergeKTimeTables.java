import java.util.*;

public class M12_MergeKTimeTables {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<List<Integer>> tables = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            List<Integer> t = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                t.add(sc.nextInt());
            }
            tables.add(t);
        }

        List<Integer> merged = mergeKTables(tables);

        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i) + (i == merged.size()-1 ? "\n" : " "));
        }
    }

    private static List<Integer> mergeKTables(List<List<Integer>> tables) {
        List<Integer> merged = new ArrayList<>();
        int K = tables.size();
        int[] idx = new int[K]; // 各列表的指標

        while (true) {
            int minVal = Integer.MAX_VALUE;
            int minTable = -1;
            boolean anyLeft = false;

            for (int i = 0; i < K; i++) {
                if (idx[i] < tables.get(i).size()) {
                    anyLeft = true;
                    if (tables.get(i).get(idx[i]) < minVal) {
                        minVal = tables.get(i).get(idx[i]);
                        minTable = i;
                    }
                }
            }

            if (!anyLeft) break; // 所有列表掃描完成

            merged.add(minVal);
            idx[minTable]++;
        }

        return merged;
    }
}
