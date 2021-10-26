import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class AntiqueDealer {

    public static int maximum = Integer.MIN_VALUE;
    public static int top = 0;
    public static int[] arr;
    public static int max(int item)
    {
        if(item > maximum)
        {
            maximum = item;
        }
        return maximum;
    }

    public static void push(int item) throws FullStackException {
        if(top < arr.length)
        {
            arr[top] = item;
            top++;
            max(item);
        }
        else
        {
            throw new FullStackException("Stack is full!!!!");
        }
    }

    public static int pop()
    {
        int item;
        if(top > 0)
        {
            item = arr[top];
            max(item);
            return item;
        }
        else {
            throw new EmptyStackException();
        }
    }
    public static void main(String args[])
    {
        int item;
        int max = Integer.MIN_VALUE;
        Scanner sc = new Scanner(System.in);

        /*Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < n; i++)
        {
            item = sc.nextInt();
            stack.push(item);
            max = max(item);
        }*/

        System.out.println("Enter the number of elements can be stored in the inventory: ");
        int n = sc.nextInt();

        arr = new int[n];
        int flag = 1;
        do{
            System.out.println("Press 1 to add element");
            System.out.println("Press 2 to remove element");
            System.out.println("Press 3 to display maximum value");
            System.out.println("Press 4 to exit");
            int choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1:
                        System.out.println("Enter the value:");
                        item = sc.nextInt();
                        push(item);
                        break;

                    case 2:
                        pop();
                        System.out.println("Item from the top is removed successfully");
                        break;

                    case 3:
                        System.out.println("Current maximum value is " + maximum);
                        System.out.println("Donya meshgin");
                        break;

                    case 4:
                        System.out.println("");
                        flag = 0;
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Wrong Choice!!!!");
                }
            } catch (FullStackException f) {
                System.out.println(f.getMessage());
            }
        }while(flag == 1);
        /*for(int i = 0; i < n; i++)
        {
            item = sc.nextInt();
            try {
                push(item);
            } catch (FullStackException e) {
                System.out.println(e.getMessage());
            }
        }*/
        //System.out.println("Current maximum is "+maximum);
    }

}
