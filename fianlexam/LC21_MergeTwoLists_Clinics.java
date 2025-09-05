import java.util.*;

public class LC21_MergeTwoLists_Clinics {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ListNode l1 = buildList(sc, n);
        ListNode l2 = buildList(sc, m);
        sc.close();

        ListNode merged = mergeTwoLists(l1, l2);

        // 輸出合併後序列
        printList(merged);
    }

    private static ListNode buildList(Scanner sc, int len) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < len; i++) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(" ");
            cur = cur.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // 接上剩餘
        tail.next = (l1 != null) ? l1 : l2;

        return dummy.next;
    }
}
