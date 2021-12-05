import java.util.Arrays;

/**
 * Assignment 3 - Part 2
 * Written by: Charit Pareshbhai Patel (40160658)
 * <p>
 * Custom implementation of ArrayList class.
 * It defines all the basic methods of ArrayList class.
 *
 * @param <E> Generic parameter to determine the type of data stored in the arraylist.
 * @author CHARIT
 */
class ArrayListCustom<E> {

    // Define INITIAL_CAPACITY, size of elements of custom ArrayList
    private static final int INITIAL_CAPACITY = 100000000;

    private int size = 0;
    private Object elementData[] = {};

    /**
     * Default constructor of custom ArrayList
     */
    public ArrayListCustom() {
        elementData = new Object[INITIAL_CAPACITY];
    }

    /**
     * Parameterized constructor of custom ArrayList
     *
     * @param capacity
     */
    public ArrayListCustom(int capacity) {
        elementData = new Object[capacity];
    }

    /**
     * add elements in custom/your own ArrayList
     * Method adds elements in ArrayListCustom.
     */
    public void add(E e) {
        if (size == elementData.length) {
            ensureCapacity(); // increase current capacity of list, make it double.
        }
        elementData[size++] = e;
    }

    /**
     * method returns element on specific index.
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        // if index is negative or greater than size of size, we throw
        // Exception.
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        return (E) elementData[index]; // return value on index.
    }

    /**
     * remove elements from custom/your own ArrayList method returns
     * <p>
     * removedElement on specific index. else it throws IndexOutOfBoundException
     * if index is negative or greater than size of size.
     */
    public Object remove(int index) {
        // if index is negative or greater than size of size, we throw an Exception.
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size "
                    + index);
        }

        Object removedElement = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--; // reduce size of ArrayListCustom after removal of element.

        return removedElement;
    }

    /**
     * Ensure capacity of custom/your own ArrayList.
     * <p>
     * Method increases capacity of list by making it double.
     */
    private void ensureCapacity() {
        int newIncreasedCapacity = elementData.length * 2;
        elementData = Arrays.copyOf(elementData, newIncreasedCapacity);
    }

    /**
     * Display custom/your own ArrayList.
     * <p>
     * Method displays all the elements in list.
     */
    public void display() {
        System.out.print("Displaying list : ");
        for (int i = 0; i < size; i++) {
            System.out.print(elementData[i] + " ");
        }
    }

    /**
     * Returns the size of the list.
     *
     * @return size of the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the index of the specific element in the array list.
     *
     * @param key element required to be searched.
     * @return index of the element.
     */
    public long indexOf(Object key) {
        int index = 0;
        //System.out.println(elementData.length);
        for (int i = 0; i < elementData.length; i++) {
            System.out.println(elementData[i]);
            if (elementData[i] == key) {
                System.out.println(index);
                return index;
            }
            //System.out.println(index);
            index++;
        }
        return -1;
    }

    public void sort(ArrayListCustom<E> list)
    {
        int q;
        int start = 0;
        int end = list.getSize();
        if(start < end)
        {
            
        }
    }
}

