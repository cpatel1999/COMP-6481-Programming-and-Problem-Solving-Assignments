import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CleverSIDC<K, V> {

    public final static long KEY_LENGTH = 100000000;
    public int method_1 = 0, method_2 = 0;
    Random random = new Random();
    ArrayListCustom<K> list;
    private LinkedHashMapCustom<Long, Integer> map;

    public CleverSIDC() {
        map = new LinkedHashMapCustom<Long, Integer>();
    }

    public void setSIDCThreshold(long size) {
        if (size > 0 && size < 100) {
            method_1 = 1;
            //randomKeysGenerator();
        } else if (size >= 100 && size <= 500000) {
            method_2 = 1;
            //randomKeysGenerator();
        } else {
            System.out.println("Threshold is very high!!!!");
            return;
        }
    }

    public void generate() {
        Long randomKey = ThreadLocalRandom.current().nextLong(100000000);
        while (map.get(randomKey) != null) {
            randomKey = ThreadLocalRandom.current().nextLong(100000000);
        }
        int randomValue = ThreadLocalRandom.current().nextInt(1000000);
        map.put(randomKey, randomValue);
    }

    public ArrayListCustom<K> allKeys() {
        list = (ArrayListCustom<K>) map.keysReturn();

        return list;
    }

    public void add(long key, int value) {
        if (method_2 == 1) {
            if (!map.containsKey(key)) {
                map.put(key, value);
            } else {
                System.out.println("Key already exists!!!!");
            }
        }
    }

    public void remove(Long key) {
        if (method_2 == 1) {
            if (map.containsKey(key)) {
                map.remove(key);
            } else {
                System.out.println("Key not found!!!!");
            }
        }
    }

    public int getValues(long key) {
        if (map.get(key) != null) {
            return map.get(key);
        } else {
            return -1;
        }
    }

    public long nextKey(long key) {
        if (map.find(key) != null) {
            LinkedHashMapCustom.Entry<Long, Integer> temp = map.find(key);
            if (temp.next != null) {
                return temp.next.key;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
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

   /* public static void main(String[] args)
    {
        CleverSIDC c = new CleverSIDC();
        System.out.println(ThreadLocalRandom.current().nextLong(100000000));
    }*/
}
