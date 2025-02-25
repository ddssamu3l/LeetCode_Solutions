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

/*
    The "trick" to solving this LeetCode problem is to use a HashMap<Node, Node> to keep track of the original nodes and their corresponding clones. This is essential because without a HashMap, we would not be able to establish the neighbor relationships between the cloned nodes correctly.
    Here's a more clear and concise explanation:
    1.
    Create a HashMap called visited to store the original nodes as keys and their clones as values. This will help us avoid creating duplicate clones of the same node.
    2.
    Define a helper function called generateClone that takes a node and the visited HashMap as parameters. This function will create a clone of the given node and its neighbors.
    3.
    Inside the generateClone function, check if the current node has already been visited. If it has, return the clone from the visited HashMap. This ensures that we don't create duplicate clones of the same node.
    4.
    If the current node has not been visited, create a new clone of the node and add it to the visited HashMap.
    5.
    Iterate through the neighbors of the current node and recursively call the generateClone function for each neighbor. Add the cloned neighbors to the clone of the current node.
    6.
    Return the clone of the current node.
    7.
    In the main cloneGraph function, call the generateClone function with the given node and an empty visited HashMap. Return the cloned graph.
    By using a HashMap to keep track of the original nodes and their clones, we can ensure that the cloned graph is a deep copy of the original graph, with all the neighbor relationships intact.
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