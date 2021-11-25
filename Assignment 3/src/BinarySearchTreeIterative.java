public class BinarySearchTreeIterative extends BaseBinaryTree implements BinarySearchTree {

    @Override
    public Node searchNode(int key) {
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
    public void insertNode(int key) {
        Node newNode = new Node(key);

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
    public void deleteNode(int key) {

    }
}
