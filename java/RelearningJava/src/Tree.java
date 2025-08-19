public class Tree {
    private Node head;

    public Tree(Node head) {
        this.head = head;
    }

    public void addNode(Node n, int value) {
        if (value < n.getValue()) {
            if (n.getLeft() == null) {
                n.setLeft(new Node(value));
            } else {
                addNode(n.getLeft(), value);
            }
        } else {
            if (n.getRight() == null) {
                n.setRight(new Node(value));
            } else {
                addNode(n.getRight(), value);
            }
        }
    }

    public void printTreeLtR(Node n) {
        if (n.getLeft() != null) {
            printTreeLtR(n.getLeft());
        }
        System.out.println(n.getValue());

        if (n.getRight() != null) {
            printTreeLtR(n.getRight());
        }
    }

    public void printTreeRtL(Node n) {
        if (n.getRight() != null) {
            printTreeRtL(n.getRight());
        }
        System.out.println(n.getValue());

        if (n.getLeft() != null) {
            printTreeRtL(n.getLeft());
        }
    }

    public boolean find(Node n, int value) {
        if (n == null) return false;
        if (n.getValue() == value) {
            return true;
        } else if (n.getValue() > value) {
            return find(n.getLeft(), value);
        } else {
            return find(n.getRight(), value);
        }
    }

    public static void main(String[] args) {
        Node head = new Node(8);
        Tree tree = new Tree(head);

        // Left Side
        tree.addNode(head, 4);
        tree.addNode(head, 2);
        tree.addNode(head, 6);
        tree.addNode(head, 1);
        tree.addNode(head, 3);
        tree.addNode(head, 5);
        tree.addNode(head, 7);

        // Right Side
        tree.addNode(head, 12);
        tree.addNode(head, 10);
        tree.addNode(head, 14);
        tree.addNode(head, 9);
        tree.addNode(head, 11);
        tree.addNode(head, 13);
        tree.addNode(head, 15);

        tree.printTreeLtR(head);
        tree.printTreeRtL(head);

        System.out.println(tree.find(head, 15));
    }
}

class Node {
    private Node left;
    private Node right;
    private int value;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setLeft(Node n) {
        this.left = n;
    }

    public void setRight(Node n) {
        this.right = n;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}