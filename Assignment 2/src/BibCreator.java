import java.io.*;
import java.util.Scanner;

public class BibCreator {

    public static int fileCount = 10;

    public static void processFilesForValidation(Scanner sc, PrintWriter IEEEWriter, PrintWriter ACMWriter, PrintWriter NJWriter, int count)
    {
        int articleCount = 0;  //To count the number of articles in the .bib file.
        PrintWriter bufferFileWriter = null;
        Scanner bufferScanner = null;
        sc.useDelimiter("@ARTICLE");  //To uniquely identify each article as each article starts with @ARTICLE.
        try {
            //Creates temporary buffer file to store each article data.
            bufferFileWriter = new PrintWriter(new FileOutputStream("E:\\CU One Drive\\OneDrive - Concordia University - Canada\\Fall 2021\\PPS\\Assignments\\Assignment 2\\Output\\bufferFile.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        while(sc.hasNext())
        {
            String articleRead = sc.next();
            articleCount++;  //Article count is incremented to track number of articles in the single .bib file.
            bufferFileWriter.println(articleRead);
            bufferFileWriter.flush();  // PrintWriter buffer is flushed, so that every time it stores new data.
            try {
                //Reads the data available in buffer file.
                bufferScanner = new Scanner(new FileInputStream("E:\\CU One Drive\\OneDrive - Concordia University - Canada\\Fall 2021\\PPS\\Assignments\\Assignment 2\\Output\\bufferFile.txt"));
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            while(bufferScanner.hasNextLine())
            {
                String lineRead = sc.nextLine();
            }
        }
    }

    public static void main(String args[]) {
        Scanner kb = new Scanner(System.in);
        Scanner[] sc = new Scanner[fileCount];
        PrintWriter ACMWriter = null;
        PrintWriter IEEEWriter = null;
        PrintWriter NJWriter = null;
        int numberOfFiles = 0;

        System.out.println("Welcome to BibCreator");
        File file = new File("E:\\CU One Drive\\OneDrive - Concordia University - Canada\\Fall 2021\\PPS\\Assignments\\Assignment 2\\Output");
        while (numberOfFiles <= fileCount) {
            try {
                sc[numberOfFiles] = new Scanner(new FileInputStream("Latex" + numberOfFiles + ".bib"));
                numberOfFiles++;
            } catch (FileNotFoundException e) {
                System.out.println("Could not open input file Latex" + numberOfFiles + ".bib for reading. Please check if file exists! Program will terminate after closing any opened files.");
            }
        }

        if (numberOfFiles == fileCount) {
            for (int i = 0; i < fileCount; i++) {
                File IEEEFile = new File("Output\\IEEE" + i + ".bib");
                File ACMFile = new File("Output\\ACM" + i + ".bib");
                File NJFile = new File("Output\\NJ" + i + ".bib");

                try {
                    IEEEWriter = new PrintWriter(new FileOutputStream(IEEEFile));
                    ACMWriter = new PrintWriter(new FileOutputStream(ACMFile));
                    NJWriter = new PrintWriter(new FileOutputStream(NJFile));
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            }
        }
    }

}
