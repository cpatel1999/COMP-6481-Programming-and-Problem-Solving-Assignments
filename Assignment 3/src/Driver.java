import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Driver {

    public static void main(String args[])
    {
        CleverSIDC<Long,Integer> cleverSIDC = new CleverSIDC<Long,Integer>();
        cleverSIDC.setSIDCThreshold(6000);
        //System.out.println(cleverSIDC.method_1);
        //System.out.println(cleverSIDC.method_2);

        Scanner sc = null;
        PrintWriter pw = null;

        long key;
        int value;
        int count = 0; //counter
        Random random = new Random();
        try {
            sc = new Scanner(new FileInputStream("NASTA_test_file1.txt"));
            pw = new PrintWriter(new FileOutputStream("output1.txt"), true);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.equals("")) {
                    key = Long.parseLong(line);
                    value = random.nextInt(100000);
                    cleverSIDC.add(key, value);
                    count++;
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Cannot find file");
        }
    }
}
