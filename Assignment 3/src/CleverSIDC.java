import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Assignment 3 - Part 2
 * Written by: Charit Pareshbhai Patel (40160658)
 * <p>
 * <p>
 * CleverSIDC is an ADT class.
 * Keys of CleverSIDC entries are long integers of 8 digits,
 * and one can retrieve the keys/values of an CleverSIDC or access a single element by its key.
 * <p>
 * CleverSIDC ADT, which automatically adapts to the dynamic content that it operates on.
 * In other words, it accepts the size (total number of students, n, identified by their 8 digits SIDC number as a key) as a parameter
 * and uses an appropriate (set of) data structure(s),
 * <p>
 * Depending upon the data structure, it calls the appropriate method from the list of methods defined in the data structure class.
 *
 * @author CHARIT
 */
public class CleverSIDC {

    public final static long KEY_LENGTH = 100000000; // Final variable to define the length of key, here 8-digits.
    private static int wrongKeyCount = 0;

    private static int rightKeyCount = 0;
    public int method_1 = 0, method_2 = 0; // Used to select data structure.
    ArrayListCustom<Long> list; //Holds arraylist, created manually from scratch.
    private LinkedHashMapCustom<Long, Integer> map; //Holds LinkedHashMap, created manually from scratch.
    private BinarySearchTreeIterative bst; //Holds Binary Search Tree (BST), created manually from scratch.

    /**
     * Default constructor to create data structures.
     */
    public CleverSIDC() {
        map = new LinkedHashMapCustom<Long, Integer>();
        bst = new BinarySearchTreeIterative();
    }

    /**
     * Returns the number of duplicate keys available in the file.
     *
     * @return count for duplicate keys.
     */
    public static int getWrongKeyCount() {
        return wrongKeyCount;
    }

    /**
     * Returns the number of unique keys available in the file.
     *
     * @return count for unique keys.
     */
    public static int getRightKeyCount() {
        return rightKeyCount;
    }

    static int nextIntInRange(int min, int max, Random rng) {
        if (min > max) {
            throw new IllegalArgumentException("Cannot draw random int from invalid range [" + min + ", " + max + "].");
        }
        int diff = max - min;
        if (diff >= 0 && diff != Integer.MAX_VALUE) {
            return (min + rng.nextInt(diff + 1));
        }
        int i;
        do {
            i = rng.nextInt();
        } while (i < min || i > max);
        return i;
    }

    /**
     * Sets the threshold value.
     * Depending upon the threshold value, it initializes the variable.
     * This variable is used to determine which data structure is used to store the data and perform the required operations.
     *
     * @param size Threshold value
     */
    public void setSIDCThreshold(long size) {
        if (size > 0 && size < 1000) {
            method_2 = 1;
        } else if (size >= 1000 && size <= 500000) {
            method_1 = 1;
        } else {
            System.out.println("Threshold is very high!!!!");
            return;
        }
    }

    /**
     * Generates random unique key and random value.
     * If key is already present in the data structure, then it generates again until the generated key is not present.
     * And it stores the <key,value> pair in an appropriate data structure.
     */
    public String generate() {
        String str = "";
        Long randomKey = ThreadLocalRandom.current().nextLong(100000000);
        if (method_1 == 1) {
            while (bst.searchNode(randomKey) != null) {  // Checks if the key is already present or not.
                randomKey = ThreadLocalRandom.current().nextLong(100000000); // If present, then generates again.
            }
            int randomValue = ThreadLocalRandom.current().nextInt(1000000);
            str = "Inserted (Key, Value) pair is (" + randomKey + ", " + randomValue + ").";
            bst.insertNode(randomKey, randomValue); // Inserts the <key,value> pair in the selected data structure.
        } else if (method_2 == 1) {
            while (map.get(randomKey) != null) {  // Checks if the key is already present or not.
                randomKey = ThreadLocalRandom.current().nextLong(100000000); // If present, then generates again.
            }
            int randomValue = ThreadLocalRandom.current().nextInt(1000000);
            str = "Inserted (Key, Value) pair is (" + randomKey + ", " + randomValue + ").";
            map.put(randomKey, randomValue); // Inserts the <key,value> pair in the selected data structure.
        }
        return str;
    }

    /**
     * Returns the sorted list of keys present in the data structure.
     *
     * @return Sorted list of keys.
     */
    public ArrayListCustom<Long> allKeys() {
        if (method_1 == 1) {
            list = bst.keysReturn();
        } else if (method_2 == 1) {
            list = map.keysReturn();
            int start = 0;
            int end = list.getSize() - 1;
            sort(start, end);
        }
        return list;
    }

    /**
     * Inserts the <key,value> pair in the data structure.
     * Inserted only when the key is not present, Otherwise displays en error message.
     *
     * @param key   Key having 8-digits.
     * @param value value corresponding to the key.
     */
    public void add(long key, int value) {
        if (method_1 == 1) {
            if (bst.searchNode(key) == null) { // Checks for the key in BST.
                bst.insertNode(key, value); // If not present, then insert.
                rightKeyCount++;
            } else {
                wrongKeyCount++;
                System.out.println("Key already exists!!!!"); //Otherwise display error message.
            }
        } else if (method_2 == 1) {
            if (!map.containsKey(key)) { // Checks for the key in BST.
                map.put(key, value); // If not present, then insert.
                rightKeyCount++;
            } else {
                wrongKeyCount++;
                System.out.println("Key already exists!!!!"); //Otherwise display error message.
            }
        }
    }

    /**
     * Removes the key from data structure.
     * If key is not present then displays an error message.
     *
     * @param key Key required to be removed.
     */
    public String remove(Long key) {
        String str = "";
        if (method_1 == 1) {
            if (bst.searchNode(key) != null) {
                bst.deleteNode(key);
                str = "Key is removed successfully.";
            } else {
                str = "Key not found!!!!";
            }
        } else if (method_2 == 1) {
            if (map.containsKey(key)) {
                map.remove(key);
                str = "Key is removed successfully.";
            } else {
                str = "Key not found!!!!";
            }
        }
        return str;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key Key
     * @return value associated with the provided key
     */
    public int getValues(long key) {
        if (method_1 == 1) {
            if (bst.searchNode(key) != null) { // Search for the key
                return bst.getValue(key); // Returns the value if key is present.
            } else {
                return -1; // Otherwise returns -1
            }
        } else if (method_2 == 1) {
            if (map.get(key) != null) {
                return map.get(key); // Returns the value if key is present.
            } else {
                return -1; // Otherwise returns -1
            }
        } else {
            return -1;
        }
    }

    /**
     * Returns the next key of the given key.
     *
     * @param key Key
     * @return next of the given key
     */
    public long nextKey(long key) {
        ArrayListCustom keyList;
        if (method_1 == 1) {
            keyList = bst.keysReturn();
        } else if (method_2 == 1) {
            keyList = map.keysReturn();
        } else {
            return 0;
        }
        int i = 0;
        int flag = 0;
        while (i < keyList.getSize()) {
            if ((Long) keyList.get(i) == key) {
                flag = 1;
                break; //Breaks the loop if key is present, stores the counter.
            }
            i++;
        }
        if (flag == 1 && i != 0) {
            return (Long) keyList.get(i + 1); //Returns the key available at (counter + 1) position.
        } else if (flag == 1 && i == 0) {
            return -1; // Returns -1 if key is at first position.
        } else {
            return 0; // Returns 0 if key is not present.
        }
    }

    /**
     * Returns the previous key of the given key.
     *
     * @param key Key
     * @return Previous key of the given key.
     */
    public long prevKey(long key) {
        ArrayListCustom keyList;
        if (method_1 == 1) {
            keyList = bst.keysReturn();
        } else if (method_2 == 1) {
            keyList = map.keysReturn(); // List of all keys
        } else {
            return 0;
        }
        int i = 0;
        int flag = 0;
        while (i < keyList.getSize()) {
            if ((Long) keyList.get(i) == key) {
                flag = 1;
                break; //Breaks the loop if key is present, stores the counter.
            }
            i++;
        }
        if (flag == 1 && i != 0) {
            return (Long) keyList.get(i - 1); //Returns the key available at (counter - 1) position.
        } else if (flag == 1 && i == 0) {
            return -1; // Returns -1 if key is at first position.
        } else {
            return 0; // Returns 0 if key is not present.
        }
    }

    /**
     * Counts the number of keys between the specified range.
     *
     * @param key1 start
     * @param key2 end
     * @return count of keys
     */
    public int rangeKey(long key1, long key2) {
        ArrayListCustom keyList = null;
        int i = 0;
        long max, min;
        int counter = 0;
        if (key1 < key2) { //Checks for the maximum and minimum key.
            min = key1;
            max = key2;
        } else {
            min = key2;
            max = key1;
        }

        if (method_1 == 1) {
            keyList = bst.keysReturn(); //List of keys available in BST.
        } else if (method_2 == 1) {
            keyList = map.keysReturn(); //List of keys available in map.
        }
        while (i < keyList.getSize()) { //Iterate key list.
            if ((Long) keyList.get(i) > min && (Long) keyList.get(i) < max) {
                counter++; // Increment key if the key is within the specified range.
            }
            i++;
        }
        return counter;
    }


    /**
     * Helper Method
     * Performs recursive sorting of arrayList.
     *
     * @param start starting index
     * @param end   End index
     */
    public void sort(int start, int end) {
        int q;
        if (start < end) {
            q = partition(start, end);
            sort(start, q);
            sort(q + 1, end);
        }
    }

    /**
     * Helper Method
     * Divides array into two part depending upon the value of pivot.
     *
     * @param start Start index
     * @param end   End index
     * @return Index
     */
    public int partition(int start, int end) {
        int init = start;
        int length = end;

        Random r = new Random();
        int pivotIndex = nextIntInRange(start, end, r);
        long pivot = list.get(pivotIndex);

        while (true) {
            while (list.get(length) > pivot && length > start) {
                length--;
            }

            while (list.get(init) < pivot && init < end) {
                init++;
            }
            if (init < length) {
                long temp;
                temp = list.get(init);
                list.set(init, list.get(length));
                list.set(length, temp);
                length--;
                init++;

            } else {
                return length;
            }
        }
    }
}