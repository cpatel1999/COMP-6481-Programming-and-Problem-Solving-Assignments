import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Assignment 3 - Part 2
 * Written by: Charit Pareshbhai Patel (40160658)
 * <p>
 * Driver class to read the data from test file.
 * It reads the data line by line and stores it into an appropriate data structure depending upon the threshold value.
 * Calls different methods to test the functionality of CleverSIDC ADT.
 *
 * @author CHARIT
 */
public class Driver {

    public static void main(String args[]) {
        CleverSIDC cleverSIDC = new CleverSIDC();
        cleverSIDC.setSIDCThreshold(99);//Sets threshold value, it is used to select the data structure to store value.

        Scanner sc = null;
        PrintWriter pw = null;

        long key;
        int value;
        int count = 0; //counter
        Random random = new Random();
        try {
            sc = new Scanner(new FileInputStream("NASTA_test_file3.txt"));
            pw = new PrintWriter(new FileOutputStream("output1.txt"), true);
            while (sc.hasNextLine()) {
                String line = sc.nextLine(); // Reads data from test file.
                if (!line.equals("")) {
                    key = Long.parseLong(line);
                    value = random.nextInt(100000); // Generates random value.
                    cleverSIDC.add(key, value); // Inserts <key,value> pair.
                    count++;
                }
            }
            pw.println("Total " + count + " keys are present in the file");
            pw.println();

            System.out.println(cleverSIDC.getWrongKeyCount());
            // All keys in sorted order
            System.out.println("All keys in sorted order: ");
            ArrayListCustom<Long> list = cleverSIDC.allKeys();
            for (int i = 0; i < list.getSize(); i++) {
                pw.println(list.get(i));
            }


            // Values corresponding to given key.
            int ret = cleverSIDC.getValues(26324890);
            if (ret == -1) {
                System.out.println("Key not present...");
            } else {
                System.out.println(ret);
            }

            // Next key of the given key,
            long retNextKey = cleverSIDC.nextKey(35967681);
            if (retNextKey == -1) {
                System.out.println("No next key...");
            } else if (retNextKey == 0) {
                System.out.println("Key doesn't exists...");
            } else {
                System.out.println(retNextKey);
            }

            // Previous key of the given key.
            long retPrevKey = cleverSIDC.prevKey(35967681);
            if (retPrevKey == -1) {
                System.out.println("No previous key...");
            } else if (retPrevKey == 0) {
                System.out.println("Key doesn't exists...");
            } else {
                System.out.println(retPrevKey);
            }

            // Displays the count of the keys between the specified range.
            System.out.println(cleverSIDC.rangeKey(33240013, 33255593));

        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        }
    }
}
