import java.util.Scanner;

public class ArrayRepeat {

    public static void repeatElementCount(int[] arr)
    {
       // int previousElement = arr[0];
        int currentElement;
        int nextElement;
        int count = 1;
        int index = 0;
        for(int i = 0; i < arr.length-1; i++)
        {
            currentElement = arr[i];
            nextElement = arr[i+1];
            if(nextElement == currentElement)
            {
                count++;
            }
            else
            {
                index = i + 1;
                if(count != 1)
                {
                    System.out.println("Value " + currentElement + " is repeated " + count + " times stating at Index " + index);
                }
                count = 1;
            }
        }
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++)
        {
            arr[i] = sc.nextInt();
        }
        repeatElementCount(arr);
    }

}
