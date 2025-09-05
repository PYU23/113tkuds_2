import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        sc.close();

        int newLen = removeElement(nums, val);
        System.out.println(newLen);
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i] + (i < newLen - 1 ? " " : ""));
        }
    }

    public static int removeElement(int[] nums, int val) {
        int write = 0;
        for (int x : nums) {
            if (x != val) nums[write++] = x;
        }
        return write;
    }
}
