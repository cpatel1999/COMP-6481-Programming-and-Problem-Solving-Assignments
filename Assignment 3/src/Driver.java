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

        // First method  --> setThreshold()
        cleverSIDC.setSIDCThreshold(999);//Sets threshold value, it is used to select the data structure to store value.

        Scanner sc = null;
        PrintWriter pw = null;

        int l_key = 112233;
        long key;
        int value;
        int count = 0; //counter
        Random random = new Random();
        //System.out.println(Math.abs(l_key.hashCode()));
        try {
            sc = new Scanner(new FileInputStream("SampleDataset3.txt"));
            pw = new PrintWriter(new FileOutputStream("NASTA_test_file1_output.txt"), true);
            while (sc.hasNextLine()) {
                String line = sc.nextLine(); // Reads data from test file.
                if (!line.equals("")) {
                    key = Long.parseLong(line);
                    value = random.nextInt(100000); // Generates random value.

                    // Forth method --> add(key, value)
                    // Inserting value
                    cleverSIDC.add(key, value); // Inserts <key,value> pair.
                    count++;
                }
            }
            pw.println("Total " + count + " keys are present in the file");
            pw.println("Total unique keys are : " + cleverSIDC.getRightKeyCount());
            pw.println("Total duplicate keys are :" + cleverSIDC.getWrongKeyCount());
            pw.println();

            //Second method --> generate()
            pw.println(cleverSIDC.generate());


            // Third method --> allKeys()
            // All keys in sorted order
            pw.println("All keys in sorted order: ");
            ArrayListCustom<Long> list = cleverSIDC.allKeys();
            for (int i = 0; i < list.getSize(); i++) {
                pw.println(list.get(i));
            }

            // Fifth method --> remove(key)
            // Removing the value
            long removeKey = 84100284;
            pw.println("Removing " + removeKey);
            pw.println(cleverSIDC.remove(removeKey));

            // Sixth method --> getValue(key)
            // Values corresponding to given key.
            long KEY = 84100284;
            int ret = cleverSIDC.getValues(KEY);
            if (ret == -1) {
                pw.println("Key " + KEY + " not present...");
            } else {
                pw.println("Value of " + KEY + " is " + ret);
            }

            // Seventh method --> nextKey(key)
            // Next key of the given key,
            KEY = 48503482;
            long retNextKey = cleverSIDC.nextKey(KEY);
            if (retNextKey == -1) {
                pw.println("No next key...");
            } else if (retNextKey == 0) {
                pw.println("Key doesn't exists...");
            } else {
                pw.println("Next key of " + KEY + " is " + retNextKey);
            }

            // Eighth method --> prevKey(key)
            // Previous key of the given key.
            KEY = 48503482;
            long retPrevKey = cleverSIDC.prevKey(KEY);
            if (retPrevKey == -1) {
                pw.println("No previous key...");
            } else if (retPrevKey == 0) {
                pw.println("Key doesn't exists...");
            } else {
                pw.println("Previous key of " + KEY + " is " + retPrevKey);
            }

            // Ninth method --> rangeKey(key1, key2)
            // Displays the count of the keys between the specified range.
            long KEY1 = 99985793;
            long KEY2 = 99986617;
            pw.println("Number of keys between " + KEY1 + " and " + KEY2 + " are " + cleverSIDC.rangeKey(KEY1, KEY2));

        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        }
    }
}