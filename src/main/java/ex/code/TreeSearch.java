package ex.code;


//Given a hierarchical structure with nodes defined by this interface:
//
//public interface Node {
//  /** Returns the child nodes, or the empty list if there are none.  Never null */
//  List<Node> getChildNodes();
//  /** The name assigned to the node.  Never null */
//  String getNodeName();
//}
//
//  Implement a depth-first search method which finds the first Node with a given Name starting from a given Node and searching all child nodes:
//
///** Find the first node in the tree with the specified name using a depth-first search, or null if it cannot be found */
//    Node findNodeByName(String name, Node startNode) {
//    // Implementation goes here
//    }
//
//    You can assume that the structure does not loop back on itself.  That is, there are no child nodes which refer back up to any parent nodes in the hierarchy.

import java.util.ArrayList;
import java.util.List;

public class TreeSearch {

  interface Node {
    List<Node> getChildNodes();
    String getNodeName();
  }

  static class NodeImpl implements Node {
    private final String name;
    private final List<Node> children;

    public NodeImpl(String name, List<Node> children) {
      this.name = name;
      this.children = children;
    }

    @Override
    public List<Node> getChildNodes() {
      return children;
    }

    @Override
    public String getNodeName() {
      return name;
    }
  }

  public Node getRootNode() {
    List<Node> nodeList= new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      nodeList.add(new NodeImpl("item " + i, null));
    }
    List<Node> list9 = new ArrayList<>();
    list9.add(nodeList.get(0));
    list9.add(nodeList.get(1));
    Node node9 = new NodeImpl("item 9", list9);

    List<Node> list10 = new ArrayList<>();
    list10.add(nodeList.get(2));
    list10.add(nodeList.get(3));
    list10.add(nodeList.get(4));
    Node node10 = new NodeImpl("item 10", list10);

    List<Node> list11 = new ArrayList<>();
    list11.add(nodeList.get(5));
    list11.add(nodeList.get(6));
    list11.add(nodeList.get(7));
    list11.add(nodeList.get(8));
    Node node11 = new NodeImpl("item 11", list11);

    List<Node> list12 = new ArrayList<>();
    list12.add(node9);
    list12.add(node10);
    list12.add(node11);
    return new NodeImpl("root", list12);
  }

  public Node findNodeByName(String name, Node startNode) {
    Node result = null;
    if (startNode.getNodeName().equals(name)) {
      result = startNode;
    } else if (startNode.getChildNodes() == null) {
      return null;
    } else {
      for (Node n : startNode.getChildNodes()) {
        result = findNodeByName(name, n);
        if (result != null) {
          break;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    TreeSearch test = new TreeSearch();
    Node res = test.findNodeByName("item 11", test.getRootNode());
    System.out.println("res = " + (res != null ? res.getNodeName() : "Not found"));
  }


}
