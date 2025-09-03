// 檔名：lt_25_reverse_k_group.java
public class lt_25_reverse {

    // 鏈表節點定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // 反轉每 k 個節點
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy;

        while (true) {
            // 找到第 k 個節點
            ListNode kth = prevGroup;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) break; // 不足 k 個節點，停止

            ListNode groupNext = kth.next;
            // 反轉這 k 個節點
            ListNode prev = groupNext;
            ListNode curr = prevGroup.next;
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // 連接上一組
            ListNode tmp = prevGroup.next;
            prevGroup.next = kth;
            prevGroup = tmp;
        }

        return dummy.next;
    }

    // 將 int[] 轉成鏈表
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : arr) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    // 印出鏈表
    public static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(" -> ");
            cur = cur.next;
        }
        System.out.println();
    }

    // 本地測試 main
    public static void main(String[] args) {
        int[][] tests = {
            {1,2,3,4,5},
            {1,2,3,4,5},
            {1,2,3,4,5},
            {1},
            {}
        };
        int[] ks = {2, 3, 1, 1, 2};

        for (int i = 0; i < tests.length; i++) {
            System.out.print("Input: ");
            for (int v : tests[i]) System.out.print(v + " ");
            System.out.println("\nk = " + ks[i]);

            ListNode head = buildList(tests[i]);
            ListNode reversed = reverseKGroup(head, ks[i]);

            System.out.print("Output: ");
            printList(reversed);
            System.out.println("----------");
        }
    }
}
