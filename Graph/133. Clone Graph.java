/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        HashMap<Node, Node> visited = new HashMap<>(); // key = The node. value = the node's clone
        return generateClone(node, visited);
    }

    private Node generateClone(Node node, HashMap<Node, Node> visited){
        if(node == null)
            return null;

        if(visited.get(node) != null) // if the current node is visited, just return its clone
            return visited.get(node);

        // build a clone of the current node
        Node nodeClone = new Node(node.val);
        // mark the current node as visited
        visited.put(node, nodeClone);

        // build the clones of the children of the current node
        for(Node child : node.neighbors){
            nodeClone.neighbors.add(generateClone(child, visited));
        }

        return nodeClone;
    }
}