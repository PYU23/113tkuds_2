// 檔名：lt_23_mergeKLists.java
import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    // 輔助方法：將陣列轉成 ListNode
    public static ListNode arrayToList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int num : arr) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }
        return dummy.next;
    }

    // 輔助方法：印出 ListNode
    public static void printList(ListNode head) {
        ListNode curr = head;
        List<String> vals = new ArrayList<>();
        while (curr != null) {
            vals.add(String.valueOf(curr.val));
            curr = curr.next;
        }
        System.out.println(vals);
    }
}

public class lt_23_mergeKLists {

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = minNode;
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // 測試資料
        int[][][] tests = {
            {{1,4,5}, {1,3,4}, {2,6}},
            {},
            {{}}  // [[]]
        };

        for (int t = 0; t < tests.length; t++) {
            int[][] listsArr = tests[t];
            ListNode[] lists = new ListNode[listsArr.length];
            for (int i = 0; i < listsArr.length; i++) {
                lists[i] = ListNode.arrayToList(listsArr[i]);
            }
            System.out.println("Test " + (t+1) + ":");
            ListNode merged = mergeKLists(lists);
            ListNode.printList(merged);
            System.out.println("----------");
        }
    }
}
