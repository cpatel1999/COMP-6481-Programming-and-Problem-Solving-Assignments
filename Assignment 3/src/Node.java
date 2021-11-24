/**
 * A node in a binary tree, containing an <code>int</code> data.
 *
 * @author CHARIT
 */
public class Node {

    int data; //Data stored in the Node.
    Node left;
    Node right;
    Node parent;
    int height;

    /**
     * Constructs a new node with the given data.
     *
     * @param data the data to store in the node
     */
    public Node(int data) {
        this.data = data;
    }

    /**
     * Returns the value stored in the Node.
     *
     * @return value stored in the Node.
     */
    public int getData() {
        return data;
    }
}
