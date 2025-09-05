import java.util.*;

public class LC24_SwapPairs_Shifts {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();
        sc.close();

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        if (!line.isEmpty()) {
            String[] tokens = line.split("\\s+");
            for (String t : tokens) {
                cur.next = new ListNode(Integer.parseInt(t));
                cur = cur.next;
            }
        }

        ListNode newHead = swapPairs(dummy.next);
        printList(newHead);
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換 a, b
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // 移到下一對前
            prev = a;
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
}
