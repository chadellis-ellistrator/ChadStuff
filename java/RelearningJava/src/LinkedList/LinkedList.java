package LinkedList;
public class LinkedList {
    private Node head;
    private Node tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void add(int value) {
        Node n = new Node(value);
        if (this.tail != null) {
            this.tail.setNext(n);
        }
        this.tail = n;

        if (this.head == null) {
            this.head = n;
        }
    }

    public void remove(int value) {
        if (this.head != null) {
            Node node = this.head;
            Node previous = node;
            while (node.getValue() != value && node.getNext() != null) {
                previous = node;
                node = node.getNext();
            }
            if (node.getValue() == value) {
                if (node == head) {
                    this.head = head.getNext();
                    if (this.tail == node) {
                        this.tail = null;
                    }
                } else {
                    previous.setNext(node.getNext());
                    if (previous.getNext() == null) {
                        this.tail = previous;
                    }
                }
            }
        }
    }

    public String toString() {
        if (head == null) {
            return "List is empty";
        } else {
            String str = String.valueOf(head.getValue());
            Node next = head.getNext();
            while (next != null) {
                str += " " + next.getValue();
                next = next.getNext();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add(1);
        ll.add(3);
        ll.add(5);
        System.out.println(ll);
        ll.remove(3);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);
        ll.remove(1);
        System.out.println(ll);
    }

}

class Node {
    private int value;
    private Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public int getValue() {
        return this.value;
    }

    public Node getNext() {
        return this.next;
    }
}
