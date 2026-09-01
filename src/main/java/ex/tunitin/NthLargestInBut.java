package ex.tunitin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NthLargestInBut {
    static class Node {
        Integer value;
        Node left;
        Node right;

        public Node(Integer value) {
            this.value = value;
        }
    }

    static class Counter {
        int counter = 0;
        int count = 0;
        Integer result = null;
        List<Integer> list;

        Counter(int count) {
            this.count = count;
            list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                list.add(Integer.MIN_VALUE);
            }
        }

        public void addLargest(int value) {
            list.add(value);
            Collections.sort(list);
            list.subList(1, list.size());
        }
    }

    private Node root;

    public static void main(String[] args) {
        NthLargestInBut bst = new NthLargestInBut();
        bst.init();
        int n = 2;
        System.out.println(n + "th largest element in BST is: " + bst.findNthLargest(n));
//        System.out.println(n + "th smallest element in BST is: " + bst.findNthSmallest(n));

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
        Counter counter = new Counter(n);
        traverse(root, counter, n, true);
        return counter.list.get(counter.list.size() - n);
    }

//    private int findNthSmallest(int n) {
//        Counter counter = new Counter(n);
//        traverse(root, counter, n, false);
//        return counter.result;
//    }


//    private void traverse(Node node, Counter c, int n, boolean largest) {
//        if (node == null || c.result != null) {
//            return;
//        }
//        Node first = node.right;
//        Node second = node.left;
//        if (!largest) {
//            first = node.left;
//            second = node.right;
//        }
//        traverse(first, c, n, largest);
//        c.counter++;
//        if (c.counter == n) {
//            c.result = node.value;
//        }
//        traverse(second, c, n, largest);
//    }

    private void traverse(Node node, Counter c, int n, boolean largest) {
        if (node == null) {
            return;
        }
        Node first = node.right;
        Node second = node.left;
        traverse(first, c, n, largest);
        c.addLargest(node.value);
        traverse(second, c, n, largest);
    }

}
