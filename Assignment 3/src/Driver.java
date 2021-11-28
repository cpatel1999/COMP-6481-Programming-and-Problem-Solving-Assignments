import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Driver {

    public static void main(String args[]) {
        CleverSIDC<Long, Integer> cleverSIDC = new CleverSIDC<Long, Integer>();
        cleverSIDC.setSIDCThreshold(99);

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
            pw.println("Total " + count + " keys are present in the file");
            pw.println();


            // All keys in sorted order
            System.out.println("All keys in sorted order: ");
            ArrayListCustom<Long> list = cleverSIDC.allKeys();
            for (int i = 0; i < list.getSize(); i++) {
                pw.println(list.get(i));
            }

/*
            // Values corresponding to given key.
            int ret = cleverSIDC.getValues(33256987);
            if (ret == -1) {
                System.out.println("Key not present...");
            } else {
                System.out.println(ret);
            }

            long retNextKey = cleverSIDC.nextKey(33240013);
            if (retNextKey == -1) {
                System.out.println("No next key...");
            } else if (retNextKey == 0) {
                System.out.println("Key doesn't exists...");
            } else {
                System.out.println(retNextKey);
            }

            long retPrevKey = cleverSIDC.prevKey(33255593);
            if(retPrevKey == -1)
            {
                System.out.println("No previous key...");
            }
            else if(retPrevKey == 0) {
                System.out.println("Key doesn't exists...");
            } else {
                System.out.println(retPrevKey);
            }

            System.out.println(cleverSIDC.rangeKey(33240013,33255593));
*/
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        }
    }
}
