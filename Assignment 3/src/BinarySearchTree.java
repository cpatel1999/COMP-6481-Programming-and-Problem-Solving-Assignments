/**
 * Assignment 3 - Part 2
 * Written by: Charit Pareshbhai Patel (40160658)
 * <p>
 * Interface for various BST implementations.
 *
 * @author CHARIT
 */
public interface BinarySearchTree {

    /**
     * Searches for a node with the given search key.
     *
     * @param key the search key
     * @return the node or <code>null</code> if no node with the given search key exists
     */
    Node searchNode(long key);

    /**
     * Inserts a node with the given key.
     *
     * @param key the key of the node to be inserted
     */
    void insertNode(long key, int value);

    /**
     * Deletes the node with the given key.
     *
     * @param key the key of the node to be deleted
     */
    void deleteNode(long key);

    /**
     * Returns all the keys as list.
     *
     * @return list of keys.
     */
    ArrayListCustom<Long> keysReturn();

    /**
     * Returns the value associated with the key.
     *
     * @param key Key required to be searched.
     * @return Value assigned to the key.
     */
    int getValue(long key);
}
