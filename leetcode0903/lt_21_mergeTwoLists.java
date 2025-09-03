// 檔名：lt_21_mergeTwoLists.java
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

public class lt_21_mergeTwoLists {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        // 接上剩餘節點
        if (list1 != null) tail.next = list1;
        if (list2 != null) tail.next = list2;

        return dummy.next;
    }

    public static void main(String[] args) {
        int[][] testList1 = {
            {1,2,4},
            {},
            {}
        };

        int[][] testList2 = {
            {1,3,4},
            {},
            {0}
        };

        for (int i = 0; i < testList1.length; i++) {
            ListNode list1 = ListNode.arrayToList(testList1[i]);
            ListNode list2 = ListNode.arrayToList(testList2[i]);

            System.out.println("Test " + (i+1) + ":");
            System.out.print("list1: "); ListNode.printList(list1);
            System.out.print("list2: "); ListNode.printList(list2);

            ListNode merged = mergeTwoLists(list1, list2);
            System.out.print("Merged: "); ListNode.printList(merged);
            System.out.println("----------");
        }
    }
}
