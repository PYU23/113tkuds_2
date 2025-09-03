// 檔名：lt_24_swap_pairs.java
public class lt_24_swap {

    // 鏈表節點定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // 交換每對相鄰節點
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            // 交換節點
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // 移動 prev 到下一對
            prev = first;
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
            {},
            {1},
            {1,2},
            {1,2,3},
            {1,2,3,4}
        };

        for (int[] test : tests) {
            System.out.print("Input: ");
            for (int v : test) System.out.print(v + " ");
            System.out.println();

            ListNode head = buildList(test);
            ListNode swapped = swapPairs(head);

            System.out.print("Output: ");
            printList(swapped);
            System.out.println("----------");
        }
    }
}
