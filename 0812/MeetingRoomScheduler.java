import java.util.*;

public class MeetingRoomScheduler {

    // 子問題 1：最少需要多少會議室
    public int minMeetingRooms(int[][] meetings) {
        if (meetings == null || meetings.length == 0) return 0;

        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0])); // 按開始時間排序
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 存會議室的結束時間

        for (int[] meeting : meetings) {
            if (!minHeap.isEmpty() && minHeap.peek() <= meeting[0]) {
                minHeap.poll(); // 重用會議室
            }
            minHeap.offer(meeting[1]);
        }

        return minHeap.size();
    }

    // 子問題 2：固定 N 個會議室，最大化會議時間
    public int maxTotalMeetingTime(int[][] meetings, int roomCount) {
        if (meetings == null || meetings.length == 0 || roomCount == 0) return 0;

        Arrays.sort(meetings, (a, b) -> Integer.compare(a[1], b[1])); // 按結束時間排序
        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>(); // 存每間會議室的結束時間
        int totalTime = 0;

        for (int[] meeting : meetings) {
            // 如果最早結束的會議室已空出，或還有空房
            if (!roomEndTimes.isEmpty() && roomEndTimes.peek() <= meeting[0]) {
                roomEndTimes.poll(); // 空出會議室
                roomEndTimes.offer(meeting[1]);
                totalTime += meeting[1] - meeting[0];
            } else if (roomEndTimes.size() < roomCount) {
                roomEndTimes.offer(meeting[1]);
                totalTime += meeting[1] - meeting[0];
            }
            // 否則這個會議跳過（無空間）
        }

        return totalTime;
    }

    public static void main(String[] args) {
        MeetingRoomScheduler mrs = new MeetingRoomScheduler();

        // 測試子問題 1
        int[][] meetings1 = {{0,30},{5,10},{15,20}};
        System.out.println(mrs.minMeetingRooms(meetings1)); // 預期 2

        int[][] meetings2 = {{9,10},{4,9},{4,17}};
        System.out.println(mrs.minMeetingRooms(meetings2)); // 預期 2

        int[][] meetings3 = {{1,5},{8,9},{8,9}};
        System.out.println(mrs.minMeetingRooms(meetings3)); // 預期 2

        // 測試子問題 2
        int[][] meetings4 = {{1,4},{2,3},{4,6}};
        System.out.println(mrs.maxTotalMeetingTime(meetings4, 1)); // 預期 5
    }
}

