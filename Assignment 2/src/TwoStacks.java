import java.util.EmptyStackException;

public class TwoStacks {

    int arr[];
    int size;
    int top1;
    int top2;

    public TwoStacks(int n) {
        arr = new int[n];
        size = n;
        top1 = (size / 2) - 1;
        top2 = size / 2;
    }

    public void push1(int x) throws FullStackException {
        if (top1 >= 0) {
            arr[top1] = x;
            top1--;
        } else {
            throw new FullStackException("Stack 1 is full");
        }
    }

    public void push2(int x) throws FullStackException {
        if (top2 < size) {
            arr[top2] = x;
            top2++;
        } else {
            throw new FullStackException("Stack 2 is full");
        }
    }

    public int pop1() {
        if (top1 < size / 2) {
            top1++;
            return arr[top1 - 1];
        } else {
            throw new EmptyStackException();
        }
    }

    public int pop2() {
        if (top1 >= size / 2) {
            top2--;
            return arr[top2 + 1];
        } else {
            throw new EmptyStackException();
        }
    }

    public static void main(String args[])
    {
        TwoStacks tss = new TwoStacks(10);
        try {
            tss.push1(10);
            tss.push1(20);
            tss.push1(30);
            tss.push1(40);
            tss.push1(50);
            tss.push1(60);
        } catch (FullStackException e) {
            e.printStackTrace();
        }
    }
}
