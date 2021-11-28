import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @param <K>
 * @param <V>
 *
 * @author CHARIT
 */
public class CleverSIDC<K, V> {

    public final static long KEY_LENGTH = 100000000;
    public int method_1 = 0, method_2 = 0;
    ArrayListCustom<Long> list;
    private LinkedHashMapCustom<Long, Integer> map;
    private BinarySearchTreeIterative bst;

    public CleverSIDC() {
        map = new LinkedHashMapCustom<Long, Integer>();
        bst = new BinarySearchTreeIterative();
    }

    public void setSIDCThreshold(long size) {
        if (size > 0 && size < 100) {
            method_1 = 1;
        } else if (size >= 100 && size <= 500000) {
            method_2 = 1;
        } else {
            System.out.println("Threshold is very high!!!!");
            return;
        }
    }

    public void generate() {
        Long randomKey = ThreadLocalRandom.current().nextLong(100000000);
        if (method_2 == 1) {
            while (bst.searchNode(randomKey) != null) {
                randomKey = ThreadLocalRandom.current().nextLong(100000000);
            }
            int randomValue = ThreadLocalRandom.current().nextInt(1000000);
            bst.insertNode(randomKey, randomValue);
        } else if (method_2 == 1) {
            while (map.get(randomKey) != null) {
                randomKey = ThreadLocalRandom.current().nextLong(100000000);
            }
            int randomValue = ThreadLocalRandom.current().nextInt(1000000);
            map.put(randomKey, randomValue);
        }
    }

    public ArrayListCustom<Long> allKeys() {
        if (method_1 == 1) {
            list = bst.keysReturn();
        } else if (method_2 == 1) {
            list = map.keysReturn();
        }

        return list;
    }

    public void add(long key, int value) {
        if (method_1 == 1) {
            if (bst.searchNode(key) == null) {
                bst.insertNode(key, value);
            } else {
                System.out.println("Key already exists!!!!");
            }
        } else if (method_2 == 1) {
            if (!map.containsKey(key)) {
                map.put(key, value);
            } else {
                System.out.println("Key already exists!!!!");
            }
        }
    }

    public void remove(Long key) {
        if (method_1 == 1) {
            if (bst.searchNode(key) != null) {
                bst.deleteNode(key);
            } else {
                System.out.println("Key not found!!!!");
            }
        } else if (method_2 == 1) {
            if (map.containsKey(key)) {
                map.remove(key);
            } else {
                System.out.println("Key not found!!!!");
            }
        }
    }

    public int getValues(long key) {
        if (method_1 == 1) {
            if (bst.searchNode(key) != null) {
                return bst.getValue(key);
            } else {
                return -1;
            }
        } else if (method_2 == 1) {
            int value = map.get(key);
            if (map.get(key) != null) {
                return value;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public long nextKey(long key) {
        if (method_1 == 1) {
            Node node;
            node = bst.searchNode(key);
            if (node != null) {
                if (node.left == null && node.right == null) {
                    return -1;
                } else if (node.left == null) {
                    return node.right.data;
                } else if (node.right == null) {
                    return node.left.data;
                } else {
                    // We can return key from the left or right node. But here I have returned a key stored in the right node
                    return node.right.data;
                }
            } else {
                return 0;
            }
        } else if (method_2 == 1) {
            LinkedHashMapCustom.Entry<Long, Integer> temp = map.find(key);
            if (temp != null) {
                if (temp.next != null) {
                    return temp.next.key;
                } else {
                    return -1;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public long prevKey(long key) {
        if (method_1 == 1) {
            Node node;
            node = bst.searchNode(key);
            if (node != null) {
                if (node.parent == null) {
                    return -1;
                } else {
                    return node.parent.data;
                }
            } else {
                return 0;
            }
        } else if (method_2 == 1) {
            ArrayListCustom keyList = map.keysReturn();
            int i = 0;
            int flag = 0;
            while (i < keyList.getSize()) {
                if ((Long) keyList.get(i) == key) {
                    flag = 1;
                    break;
                }
                i++;
            }
            if (flag == 1 && i != 0) {
                return (Long) keyList.get(i - 1);
            } else if (flag == 1 && i == 0) {
                return -1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int rangeKey(long key1, long key2) {
        ArrayListCustom keyList = null;
        int i = 0;
        long max, min;
        int counter = 0;
        if (key1 < key2) {
            min = key1;
            max = key2;
        } else {
            min = key2;
            max = key1;
        }

        if(method_1 == 1)
        {
            keyList = bst.keysReturn();
        }
        else if(method_2 == 1) {
            keyList = map.keysReturn();
        }
        while (i < keyList.getSize()) {
            if ((Long) keyList.get(i) > min && (Long) keyList.get(i) < max) {
                counter++;
            }
            i++;
        }
        return counter;
    }

    public boolean contains(long key) {
        return map.containsKey(key);
    }

    public void display() {
        map.display();
    }

    public void randomKeysGenerator() {
        ArrayListCustom<Long> arrayListCustom = new ArrayListCustom<Long>();
        for (long i = 0; i < KEY_LENGTH; i++) {
            arrayListCustom.add(i);
        }
        Collections.shuffle((List<?>) arrayListCustom);
    }
}
