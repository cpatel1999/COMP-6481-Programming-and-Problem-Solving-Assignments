import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

public class RandomKeysGenerator {

    public static void main(String[] args) {

        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new FileOutputStream("SampleDataset5.txt"), true);
            for (int i = 0; i < 10000; i++) {
                int randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999999 + 1);
                pw.println(Long.toString(randomNum));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        }
    }

}
