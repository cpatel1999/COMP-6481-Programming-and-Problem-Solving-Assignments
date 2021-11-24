public class BinaryTree {
    public Node root;

    /**
     * Inserts a node with the given value at the root. If the root has children, these will be moved
     * to the node to be inserted.
     *
     * @param value the value of the node to be inserted
     */
    public Node addRoot(int value) {
        Node newValue = new Node(value);
        if (root != null) {
            throw new IllegalStateException("Root already defined");
        }

        root = newValue;
        return root;
    }

    /**
     * Inserts a node with the given value as left or right child of the given parent node. If the
     * chosen child already exists, the new node is inserted between the parent node and the child.
     *
     * @param value the value of the node to be inserted
     * @param parent the parent under which to insert the new node
     * @param side whether to insert the node as left or right child
     */
    public Node insertNode(int value, Node parent, Side side) {
        Node current = new Node(value);
        current.parent = parent;

        switch (side) {
            case LEFT:
                if (parent.left != null) {
                    current.left = parent.left;
                    current.left.parent = current;

                }
                parent.left = current;
                break;

            case RIGHT:
                if (parent.right != null) {
                    current.right = parent.right;
                    current.right.parent = current;
                }
                parent.right = current;
                break;

            default:
                throw new IllegalStateException();
        }
        return current;
    }

    /**
     * Deletes the specified node from the tree.
     *
     * @param node the node to be deleted
     */
    public void deleteNode(Node node) {
        if (node.parent == null && node != root) {
            throw new IllegalStateException("Node has no parent and is not a root");
        }

        if (node.left == null && node.right == null) {
            setParentsChild(node, null);
        } else if (node.left == null) {
            setParentsChild(node, node.right);
        } else if (node.right == null) {
            setParentsChild(node, node.left);
        } else {
            removeNodeWithTwoChildren(node);
        }
        node.left = null;
        node.right = null;
        node.parent = null;
    }

    /**
     * Removes a node with two children.
     *
     * <p>Strategy: Set left child tree to deleted position. Append right child tree to right-most
     * child of left tree.
     *
     * @param node the node to remove
     */
    public void removeNodeWithTwoChildren(Node node) {
        Node leftTree = node.left;
        Node rightTree = node.right;

        setParentsChild(node, leftTree);

        // find right-most child of left tree
        Node rightMostChildOfLeftTree = leftTree;
        while (rightMostChildOfLeftTree.right != null) {
            rightMostChildOfLeftTree = rightMostChildOfLeftTree.right;
        }

        // append right tree to right child
        rightMostChildOfLeftTree.right = rightTree;
        rightTree.parent = rightMostChildOfLeftTree;
    }
    
    public void setParentsChild(Node node, Node child) {
        if (node == root) {
            root = child;
            if (child != null) {
                child.parent = null;
            }
            return;
        }
        if (node.parent.left == node) {
            node.parent.left = child;
        } else if (node.parent.right == node) {
            node.parent.right = child;
        } else {
            throw new IllegalStateException("Illegal state...");
        }
        if (child != null) {
            child.parent = node.parent;
        }
    }

    public enum Side {
        LEFT,
        RIGHT
    }

    private class Node {
        int data;
        Node left;
        Node right;
        Node parent;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }
    }
}
