// 檔名：lt_20_valid.java
import java.util.*;

public class lt_20_valid {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[] testCases = {
            "()[]{}", 
            "(]", 
            "([{}])", 
            "(((", 
            "{[()]}", 
            "{[(])}"
        };

        for (String s : testCases) {
            System.out.println("Input: " + s + " -> " + isValid(s));
        }
    }
}
