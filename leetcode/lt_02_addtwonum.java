// lt_02_addtwonum.java
public class lt_02_addtwonum {
    // 定義 ListNode
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 解法
    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(0);
            ListNode curr = dummyHead;
            int carry = 0;

            while (l1 != null || l2 != null || carry != 0) {
                int x = (l1 != null) ? l1.val : 0;
                int y = (l2 != null) ? l2.val : 0;
                int sum = x + y + carry;

                carry = sum / 10;
                curr.next = new ListNode(sum % 10);
                curr = curr.next;

                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;
            }
            return dummyHead.next;
        }
    }

    // 測試 main()
    public static void main(String[] args) {
        // l1 = [2,4,3] 代表數字 342
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        // l2 = [5,6,4] 代表數字 465
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        Solution sol = new Solution();
        ListNode result = sol.addTwoNumbers(l1, l2);

        // 輸出結果
        System.out.print("Result: ");
        while (result != null) {
            System.out.print(result.val);
            if (result.next != null) System.out.print(" -> ");
            result = result.next;
        }
    }
}
