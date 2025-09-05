import java.util.*;

public class LC25_ReverseKGroup_Shifts {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
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

        ListNode newHead = reverseKGroup(dummy.next, k);
        printList(newHead);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (true) {
            ListNode node = prev;
            // 檢查是否有 k 個節點
            for (int i = 0; i < k && node != null; i++) node = node.next;
            if (node == null) break;

            ListNode start = prev.next;
            ListNode next = node.next;

            // 反轉 start ~ node
            ListNode prevNode = next;
            ListNode curr = start;
            while (curr != next) {
                ListNode tmp = curr.next;
                curr.next = prevNode;
                prevNode = curr;
                curr = tmp;
            }

            prev.next = node; // 連回前段
            prev = start;     // 移到下一段前
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
