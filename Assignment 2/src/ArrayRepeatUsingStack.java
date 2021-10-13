import java.util.Scanner;
import java.util.Stack;

public class ArrayRepeatUsingStack {

    public static void arrayRepeat(Stack<Integer> stack)
    {
        int firstElement = stack.pop();
        int currentElement;
        int count = 1;
        int index = stack.size() - 1;
        //for(int i = stack.size() - 1; i > 0; i--)
        int i = stack.size() - 1;
        while (stack.size() != 0)
        {
            currentElement = stack.pop();
            if(currentElement == firstElement)
            {
                count++;
                index = i;
                if(stack.size() == 0)
                {
                    System.out.println("Value " + firstElement + " is repeated " + count + " times stating at Index " + index);
                }
            }
            else
            {
                if(count != 1)
                {
                    System.out.println("Value " + firstElement + " is repeated " + count + " times stating at Index " + index);
                }
                count = 1;
                firstElement = currentElement;
            }
            i--;
        }
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<Integer> stack = new Stack<Integer>();

        for(int i = 0; i < n; i++)
        {
            stack.push(sc.nextInt());
        }
        System.out.println(stack.size());
        int index = stack.size() - 1;
        System.out.println(index);
        arrayRepeat(stack);
    }

}
