import java.util.*;

public class LC23_MergeKLists_Hospitals {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();

        ListNode[] lists = new ListNode[k];

        for (int i = 0; i < k; i++) {
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            String[] tokens = sc.nextLine().trim().split("\\s+");
            for (String t : tokens) {
                if (t.equals("-1")) break;
                cur.next = new ListNode(Integer.parseInt(t));
                cur = cur.next;
            }
            lists[i] = dummy.next;
        }
        sc.close();

        ListNode merged = mergeKLists(lists);
        printList(merged);
    }

    private static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(" ");
            cur = cur.next;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        // 將非空頭節點放入堆
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) pq.offer(node.next);
        }

        return dummy.next;
    }
}
