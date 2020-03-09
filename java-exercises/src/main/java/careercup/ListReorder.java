package careercup;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *  https://www.careercup.com/question?id=5701630599626752
 *  https://leetcode.com/problems/reorder-list/
 *
 *  LinkedList :
 * Input : A>B>C>D>E
 * Output: A>E>B>D>C
 *
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 *  Thoughts:
 *      What kind of list is this? Do we construct the list ourselves or assume we are provided with it?
 *      If we build the list ourselves we could use Deque to access both ends
 *      Do we know the size? Do we have index access.
 *
 *      Assuming we have nodes with only a next pointer.
 *          - iterate the list, and save pointers to L0, L1, L2, LN-1, LN, then manually reconnect the pointers
 *           - L0->Ln, Ln->L1, L1->Ln-1, Ln-1->L2
 *
 *             first iteration:
 *       -> L0
 *       get the tail, and place it after L0
 *       iterate the whole list until el.next == null
 *       L0 -> Ln
 *       Ln -> L1
 *       Ln-1 -> null
 *
 *       second iteration:
 *       -> L1
 *
 *       1/2N
 *         Ni - 1
 *         insert 3 * O(1)
 *       -> O(N^2)
 *
 *       Improve: track end of list and
 *
 *       1/2N (+ N -1 to get lasts)
 *         insert 4 * O(1) (update the last)
 *       -> O(N)
 *
 *       Space: O(N)
 *
 *       Todo use LinkedList??
 *
 *   Test:
 *   input    A>B>C>D>E
 *   stack:   A->B->C->D->E
 *   size:    5
 *
 *   loop until i is 1 ?
 *   i = 0:   tail=E, tailM1=D,
 *   input    A>E>B>C>D
 *            |
 *
 *   i = 1:   tail=D, tailM1=C,
 *   input    A>E>B>D>C
 *                |
 *
 *
 */
public class ListReorder {
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
        }
    }

    static void reorder(Node head) {
        Deque<Node> stack = new ArrayDeque<>();
        // 1/2?
        int size = 0;
        for (Node current = head; current != null; current = current.next) {
            stack.push(current);
            size++;
        }

        Node current = head;
        for (int i = 0; i < size / 2; i++) {
            Node tail = stack.pop();
            Node tailM1 = stack.peek();

            // Ln -> L1,
            tail.next = current.next;
            // L0 -> Ln
            current.next = tail;
            // Ln-1 -> null
            tailM1.next = null;

            // update the current (head)
            current = tail.next;
        }
    }


    public static void main(String[] args) {

        Node a =  new Node('A');
        Node b =  new Node('B');
        Node c =  new Node('C');
        Node d =  new Node('D');
        Node e =  new Node('E');

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        reorder(a);

        for (Node current = a; current != null; current = current.next) {
            System.out.println(current.data + " >");
        }
    }
}
