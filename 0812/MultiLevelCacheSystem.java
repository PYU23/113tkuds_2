import java.util.*;

public class MultiLevelCacheSystem {
    private static class CacheEntry {
        int key;
        String value;
        int freq;
        long timestamp;
        int level;

        CacheEntry(int key, String value, int level) {
            this.key = key;
            this.value = value;
            this.level = level;
            this.freq = 1;
            this.timestamp = System.nanoTime();
        }
    }

    private static class CacheLevel {
        int capacity;
        int cost;
        PriorityQueue<CacheEntry> heap;

        CacheLevel(int capacity, int cost) {
            this.capacity = capacity;
            this.cost = cost;
            this.heap = new PriorityQueue<>(
                (a, b) -> a.freq != b.freq ? a.freq - b.freq : Long.compare(a.timestamp, b.timestamp)
            );
        }
    }

    private CacheLevel[] levels;
    private Map<Integer, CacheEntry> map;

    public MultiLevelCacheSystem() {
        levels = new CacheLevel[3];
        levels[0] = new CacheLevel(2, 1);   // L1
        levels[1] = new CacheLevel(5, 3);   // L2
        levels[2] = new CacheLevel(10, 10); // L3
        map = new HashMap<>();
    }

    public String get(int key) {
        if (!map.containsKey(key)) return null;
        CacheEntry entry = map.get(key);
        entry.freq++;
        entry.timestamp = System.nanoTime();
        maybePromote(entry);
        return entry.value;
    }

    public void put(int key, String value) {
        if (map.containsKey(key)) {
            CacheEntry entry = map.get(key);
            entry.value = value;
            get(key); // 更新 freq & timestamp
            return;
        }

        // 新資料先嘗試放在 L1
        CacheEntry entry = new CacheEntry(key, value, 1);
        map.put(key, entry);
        addToLevel(0, entry);
    }

    private void addToLevel(int levelIndex, CacheEntry entry) {
        CacheLevel level = levels[levelIndex];
        if (level.heap.size() >= level.capacity) {
            CacheEntry evicted = level.heap.poll();
            if (levelIndex < 2) {
                evicted.level = levelIndex + 1;
                addToLevel(levelIndex + 1, evicted);
            } else {
                map.remove(evicted.key);
            }
        }
        entry.level = levelIndex + 1; // 1-based for readability
        level.heap.offer(entry);
    }

    private void maybePromote(CacheEntry entry) {
        int currentLevel = entry.level - 1; // 0-based index
        if (currentLevel == 0) return; // Already in L1

        CacheLevel curr = levels[currentLevel];
        CacheLevel upper = levels[currentLevel - 1];

        // 如果頻率比當前層最小值高很多 → 上移
        if (!upper.heap.isEmpty() &&
            entry.freq > upper.heap.peek().freq) {
            curr.heap.remove(entry);
            addToLevel(currentLevel - 1, entry);
        }
    }

    public void printState() {
        for (int i = 0; i < 3; i++) {
            System.out.print("L" + (i+1) + ": ");
            for (CacheEntry e : levels[i].heap) {
                System.out.print("(" + e.key + ",f=" + e.freq + ") ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printState();

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.printState();

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printState();
    }
}
