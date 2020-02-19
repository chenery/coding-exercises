package stack.balancedbrackets;

import java.util.Stack;

/**
 *  https://www.hackerrank.com/challenges/ctci-balanced-brackets/problem
 *  todo can improve the code, by pushing the closing bracket onto the stack
 *  Java Deque is preferred
 *  O(n) where n is the length of the string
 */
public class BalancedBrackets {
    static String isBalanced(String s) {
        int length = s.length();
        System.out.println("s: " + s + " length - " + length);
        if (length % 2 == 1) {
            System.out.println("NO - ODD");
            return "NO";
        }
        Stack<Character> brackets = new Stack<>();
        for (int i = 0; i < length; i++) {
            char current = s.charAt(i);
            if (current == '(' || current == '{' || current == '[') {
                // System.out.println("push - " + current);
                brackets.push(current);
            } else {
                if (brackets.isEmpty()) {
                    return "NO";
                }
                char opening = brackets.pop();
                // System.out.println("opening - " + opening + " current - " + current);
                if ((opening == '(' && current != ')')
                        || (opening == '{' && current != '}')
                        || (opening == '[' && current != ']')) {
                    return "NO";
                }
            }
        }
        return brackets.isEmpty() ? "YES" : "NO";
    }

    public static void main(String[] args) {

        // NO
        System.out.println(isBalanced("}][}}(}][))]"));

        // YES
        System.out.println(isBalanced("[](){()}"));

        // YES
        System.out.println(isBalanced("({}([][]))[]()"));

        // YES
        System.out.println(isBalanced("()"));

        // NO
        System.out.println(isBalanced(")"));

        // NO
        System.out.println(isBalanced("{{{{"));

        // NO
        System.out.println(isBalanced("}()()()(())"));
    }
}
