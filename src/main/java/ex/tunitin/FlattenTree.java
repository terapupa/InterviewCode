package ex.tunitin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlattenTree {

    static class Node {
        String value;
        List<Node> children;
    }

    private final List<Node> nodeList = new ArrayList<>();


    public static void main(String[] args) {
        FlattenTree flattenTree = new FlattenTree();
        Node root = flattenTree.init();
        flattenTree.flatten(root);
        flattenTree.printFlattenedTree();

    }

    private void printFlattenedTree() {
        System.out.println("Flattened tree: " + nodeList.stream().map(node -> node.value).collect(Collectors.joining(",")));
    }

    private void flatten(Node root) {
        if (root != null) {
            nodeList.add(root);
            if (root.children != null) {
                for (Node child : root.children) {
                    flatten(child);
                }
            }
        }
    }

    private Node init() {
        Node rootNode = new Node();
        rootNode.value = "A";
        List<Node> childrenNodes = new ArrayList<>();
        rootNode.children = childrenNodes;
        Node node1 = new Node();
        node1.value = "B";
        Node node2 = new Node();
        node2.value = "C";
        childrenNodes.add(node1);
        childrenNodes.add(node2);

        childrenNodes = new ArrayList<>();
        Node node3 = new Node();
        node3.value = "D";
        Node node4 = new Node();
        node4.value = "E";

        childrenNodes.add(node3);
        childrenNodes.add(node4);
        node1.children = childrenNodes;

        childrenNodes = new ArrayList<>();
        Node node5 = new Node();
        node5.value = "F";
        childrenNodes.add(node5);
        node2.children = childrenNodes;

        return rootNode;
    }
}
