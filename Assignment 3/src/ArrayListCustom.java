import java.util.Arrays;
import java.util.List;

class ArrayListCustom<E>{

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
     * Main method to test ArrayListCustom functionality.
     */
    public static void main(String... a) {
        ArrayListCustom<Integer> list = new ArrayListCustom<Integer>();
        //Add elements in custom ArrayList
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);
        list.add(2);

        //Display custom ArrayList
        list.display();
        System.out.println("\nelement at index in custom ArrayList > " + 1 + " = " + list.get(1));

        //Remove element from custom ArrayList
        System.out.println("element removed from index " + 1 + " = "
                + list.remove(1));

        //Again display custom ArrayList
        System.out
                .println("\nlet's display custom ArrayList again after removal at index 1");

        list.display();

        // list.remove(11); //will throw IndexOutOfBoundsException, because
        // there is no element to remove on index 11.
        // list.get(11); //will throw IndexOutOfBoundsException, because there
        // is no element to get on index 11.
    }
}

