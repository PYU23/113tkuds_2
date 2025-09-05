import java.util.*;

public class LC19_RemoveNth_Node_Clinic {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }
        int k = sc.nextInt();
        sc.close();

        ListNode newHead = removeNthFromEnd(dummy.next, k);

        // 輸出刪除後的序列
        cur = newHead;
        while (cur != null) {
            System.out.print(cur.val + (cur.next != null ? " " : ""));
            cur = cur.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        // fast 先走 n 步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // fast 到尾時，slow 正好在待刪前一節點
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除節點
        slow.next = slow.next.next;

        return dummy.next;
    }
}
