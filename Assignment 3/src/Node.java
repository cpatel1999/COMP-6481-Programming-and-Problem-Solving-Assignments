/**
 * A node in a binary tree, containing an <code>int</code> data.
 *
 * @author CHARIT
 */
public class Node {

    long data; //Data stored in the Node.
    int value;
    Node left;
    Node right;

    Node parent;
    int height;

    /**
     * Constructs a new node with the given data.
     *
     * @param data the data to store in the node
     */
    public Node(long data, int value) {
        this.data = data;
        this.value = value;
    }
}
