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

public class lt_19_removeNthFromEnd {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy;
        ListNode second = dummy;

        // first 指針先走 n+1 步
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // first 與 second 同時移動，直到 first 到尾
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // 刪除倒數第 n 個節點
        second.next = second.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {1},
            {1,2},
            {1,2,3,4,5},
            {1,2,3}
        };

        int[] ns = {1, 1, 2, 3};

        for (int i = 0; i < tests.length; i++) {
            ListNode head = ListNode.arrayToList(tests[i]);
            int n = ns[i];

            System.out.println("Test " + (i+1) + ":");
            System.out.print("Original list: "); ListNode.printList(head);
            System.out.println("Remove nth from end: " + n);

            ListNode updated = removeNthFromEnd(head, n);
            System.out.print("Updated list: "); ListNode.printList(updated);
            System.out.println("----------");
        }
    }
}
