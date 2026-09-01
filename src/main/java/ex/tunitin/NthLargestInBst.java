package ex.tunitin;

public class NthLargestInBst {
    static class Node {
        Integer value;
        Node left;
        Node right;

        public Node(Integer value) {
            this.value = value;
        }
    }

    static class Counter {
        int count = 0;
        Integer result = null;
    }

    private Node root;

    public static void main(String[] args) {
        NthLargestInBst bst = new NthLargestInBst();
        bst.init();
        int n = 1;
        System.out.println(n + "th largest element in BST is: " + bst.findNthLargest(n));
        System.out.println(n + "th smallest element in BST is: " + bst.findNthSmallest(n));

    }

    private void insert(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        Node current = root;
        while (true) {
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new Node(value);
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(value);
                    return;
                }
                current = current.right;
            }
        }
    }

    private void init() {
        insert(8);
        insert(3);
        insert(10);
        insert(1);
        insert(6);
        insert(14);
        insert(4);
        insert(7);
        insert(13);
    }

    private int findNthLargest(int n) {
        Counter counter = new Counter();
        traverse(root, counter, n, true);
        return counter.result;
    }

    private int findNthSmallest(int n) {
        Counter counter = new Counter();
        traverse(root, counter, n, false);
        return counter.result;
    }


    private void traverse(Node node, Counter c, int n, boolean largest) {
        if (node == null || c.result != null) {
            return;
        }
        Node first = node.right;
        Node second = node.left;
        if (!largest) {
            first = node.left;
            second = node.right;
        }
        traverse(first, c, n, largest);
        c.count++;
        if (c.count == n) {
            c.result = node.value;
        }
        traverse(second, c, n, largest);
    }
}
