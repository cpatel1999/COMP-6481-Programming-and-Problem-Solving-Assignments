public class BinarySearchTreeIterative extends BaseBinaryTree implements BinarySearchTree {

    ArrayListCustom<Long> list = new ArrayListCustom<Long>();
    @Override
    public Node searchNode(long key) {
        Node node = root;
        while (node != null) {
            if (node.data == key) {
                return node;
            } else if (key < node.data) {
                node = node.left;
            } else if (key > node.data) {
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public ArrayListCustom<Long> keysReturn() {
        keysReturn(root);
        return list;
    }

    /**
     * Recursive inorder traversal of BST. It traverses the tree in left-->root-->right order.
     *
     * @param node Root node.
     */
    public void keysReturn(Node node)
    {
        if(node == null)
        {
            return;
        }
        keysReturn(node.left);
        list.add(node.data);
        keysReturn(node.right);
    }

    @Override
    public void insertNode(long key, int value) {
        Node newNode = new Node(key,value);

        if (root == null) {
            root = newNode;
            return;
        }

        Node node = root;
        while (true) {
            if (key < node.data) {
                if (node.left != null) {
                    node = node.left;
                } else {
                    node.left = newNode;
                    return;
                }
            } else if (key > node.data) {
                if (node.right != null) {
                    node = node.right;
                } else {
                    node.right = newNode;
                    return;
                }
            } else {
                throw new IllegalArgumentException("Value already present!!!!");
            }
        }
    }

    @Override
    public void deleteNode(long key) {
    Node node = root;
    Node parent = null;

    // Find the node to be deleted
    while (node != null && node.data != key) {
        // Traverse the tree to the left or right depending on the key
        parent = node;
        if (key < node.data) {
            node = node.left;
        } else {
            node = node.right;
        }
    }

    // Node not found?
    if (node == null) {
        return;
    }

    // At this point, "node" is the node to be deleted

    // Node has at most one child --> replace node by its single child
    if (node.left == null || node.right == null) {
        deleteNodeWithZeroOrOneChild(key, node, parent);
    }

    // Node has two children
    else {
        deleteNodeWithTwoChildren(node);
    }
}



    private void deleteNodeWithZeroOrOneChild(long key, Node node, Node parent) {
        Node singleChild = node.left != null ? node.left : node.right;

        if (node == root) {
            root = singleChild;
        } else if (key < parent.data) {
            parent.left = singleChild;
        } else {
            parent.right = singleChild;
        }
    }

    private void deleteNodeWithTwoChildren(Node node) {
        // Find minimum node of right subtree ("inorder successor" of current node)
        Node inOrderSuccessor = node.right;
        Node inOrderSuccessorParent = node;
        while (inOrderSuccessor.left != null) {
            inOrderSuccessorParent = inOrderSuccessor;
            inOrderSuccessor = inOrderSuccessor.left;
        }

        // Copy inorder successor's data to current node
        node.data = inOrderSuccessor.data;

        // Delete inorder successor

        // Case a) Inorder successor is the deleted node's right child
        if (inOrderSuccessor == node.right) {
            // --> Replace right child with inorder successor's right child
            node.right = inOrderSuccessor.right;
        }

        // Case b) Inorder successor is further down, meaning, it's a left child
        else {
            // --> Replace inorder successor's parent's left child
            //     with inorder successor's right child
            inOrderSuccessorParent.left = inOrderSuccessor.right;
        }
    }
}