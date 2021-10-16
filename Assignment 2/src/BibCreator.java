import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BibCreator {

    public static int fileCount = 10;
    //These static variables are used to track data fields available in .bib file.
    public static String author = "author";
    public static String journal = "journal";
    public static String title = "title";
    public static String year = "year";
    public static String volume = "volume";
    public static String pages = "pages";
    public static String keywords = "keywords";
    public static String doi = "doi";
    public static String issn = "ISSN";
    public static String month = "month";

    public static void processFilesForValidation(Scanner sc, PrintWriter IEEEWriter, PrintWriter ACMWriter, PrintWriter NJWriter, int count) {
        String l_author = "";
        String[] l_authorNamesArray;
        String l_journal = "";
        String l_title = "";
        String l_year = "";
        String l_volume = "";
        String l_pages = "";
        String l_keywords = "";
        String l_doi = "";
        String l_issn = "";
        String l_month = "";

        int articleCount = 0;  //To count the number of articles in the .bib file.
        PrintWriter bufferFileWriter = null; // To write into buffer file.
        Scanner bufferScanner = null; // To read from buffer file.
        sc.useDelimiter("@ARTICLE");  //To uniquely identify each article as each article starts with @ARTICLE.
        try {
            //Creates temporary buffer file to store each article data.
            bufferFileWriter = new PrintWriter(new FileOutputStream("E:\\CU One Drive\\OneDrive - Concordia University - Canada\\Fall 2021\\PPS\\Assignments\\Assignment 2\\Output\\bufferFile.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        while (sc.hasNext()) {
            String articleJsonRead = sc.next();
            articleCount++;  //Article count is incremented to track number of articles in the single .bib file.
            bufferFileWriter.println(articleJsonRead);
            bufferFileWriter.flush();  // PrintWriter buffer is flushed, so that every time it stores new article data.
            try {
                //Reads the data available in buffer file.
                bufferScanner = new Scanner(new FileInputStream("E:\\CU One Drive\\OneDrive - Concordia University - Canada\\Fall 2021\\PPS\\Assignments\\Assignment 2\\Output\\bufferFile.txt"));
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

            try {
                while (bufferScanner.hasNextLine()) {
                    //Reads the line from buffer file.
                    String lineRead = sc.nextLine();
                    //Checks whether the line contains author field.
                    if (lineRead.contains(author)) {
                        if (lineRead.contains("{}")) //Check for an empty field.
                        {
                            bufferScanner.close();
                            throw new FileInvalidException("Error: Detected Empty Field!\n ======================================\n\n"
                                    + "Problem detected with input file: Latex" + count + ".bib\n"
                                    + "File is invalid: Field " + author + " is empty in article number " + articleCount + "."
                                    + "Processing stopped at this point."
                                    + "Other empty fields may be present as well!");
                        } else {
                            //Fetches author names joined using "and" string.
                            l_author = lineRead.substring(8, lineRead.length() - 3);
                            //Array of author names.
                            l_authorNamesArray = parseAuthors(l_author);
                        }
                    }
                }
            } catch (FileInvalidException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * Helper method to parse Authors into an array
     *
     * @param p_author a String representing all the authors
     * @return a String array containing the authors separated
     */
    public static String[] parseAuthors(String p_author) {
        //Tokenizes the string of author names.
        StringTokenizer l_tokens = new StringTokenizer(p_author);
        String l_author = "";
        //Stores author names separated by "and" string.
        String[] l_authorNamesArray = new String[0];
        while (l_tokens.hasMoreTokens()) {
            String l_nextToken = l_tokens.nextToken();
            if (l_nextToken.equalsIgnoreCase("and")) {
                //Adds the author name into the array of author names string.
                l_authorNamesArray = appendToStringArray(l_authorNamesArray, l_author);
                //Removes the string data of previous author stored in l_author field to store next author details.
                l_author = "";
                continue;
            } else {
                l_author += " " + l_nextToken;
            }
        }
        //Adds the last author name into the array of author names string.
        l_authorNamesArray = appendToStringArray(l_authorNamesArray, l_author);
        return l_authorNamesArray;
    }

    /**
     * Appends a String to a String array and return that array
     *
     * @param p_authorNamesArray a String array containing the original array
     * @param p_author           a String containing the element to be appended
     * @return a String array with the element appended
     */
    public static String[] appendToStringArray(String[] p_authorNamesArray, String p_author) {
        // Creating an array with one more element
        String[] newArray = new String[p_authorNamesArray.length + 1];

        // Copying previous elements reference into new array (Shallow copy)
        int index = 0;
        while (index < p_authorNamesArray.length) {
            newArray[index] = p_authorNamesArray[index];
            index++;
        }

        // when it reaches here, index is at stringArray max index + 1
        newArray[index] = p_author;

        // return the array
        return newArray;
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
