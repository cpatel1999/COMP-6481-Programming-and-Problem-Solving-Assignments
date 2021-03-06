import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Assignment 1
 *
 * @author CHARIT
 * Written by: Charit Patel, id: 40160658
 * <p>
 * The main task of this tool is read and process a given .bib file (which has one or more articles) and
 * create 3 different files with the correct reference formats for IEEE, ACM and NJ.
 * It also handles necessary exceptions along with the exceptions mentioned in the problem statement of the assignment.
 */
public class BibCreator {

    public static int fileCount = 10;
    //These static variables are used to track data fields available in .bib file.
    public static String author = "author";
    public static String journal = "journal";
    public static String title = "title";
    public static String year = "year";
    public static String volume = "volume";
    public static String number = "number";
    public static String pages = "pages";
    public static String keywords = "keywords";
    public static String doi = "doi";
    public static String issn = "ISSN";
    public static String month = "month";
    public static int invalidFiles = 0;
    public static Scanner kb = new Scanner(System.in);

    /**
     * This is the most important method for the working of the entire application.
     * It reads each .bib file having json like structure and validate them.
     * If files have valid structure then method parses them so that the data can be written in the three different formats.
     * 1. IEEE
     * 2. ACM
     * 3. NJ
     * <p>
     * Here I have created temporary file bufferFile.txt to store and treat each article.
     *
     * @param sc         Scanner class object to read from the file.
     * @param IEEEWriter PrintWriter class object to write into IEEEx.json file.
     * @param ACMWriter  PrintWriter class object to write into ACMx.json file.
     * @param NJWriter   PrintWriter class object to write into NJx.json file.
     * @param IEEEFile   File class object for IEEEx.json file.
     * @param ACMFile    File class object for ACMx.json file.
     * @param NJFile     File class object for NJx.json file.
     * @param count      File number.
     */
    public static void processFilesForValidation(Scanner sc, PrintWriter IEEEWriter, PrintWriter ACMWriter, PrintWriter NJWriter, File IEEEFile, File ACMFile, File NJFile, int count) {

        String IEEE;
        String ACM;
        String NJ;
        String[] l_authorNamesArray = {};  //To store author names
        String l_author = "", l_journal = "", l_title = "", l_year = "", l_volume = "";
        String l_number = "", l_pages = "", l_keywords = "", l_doi = "", l_issn = "";
        String l_month = "";

        int articleCount = 0;  //To count the number of articles in the .bib file.
        PrintWriter bufferFileWriter = null; // To write into buffer file.
        Scanner bufferScanner = null; // To read from buffer file.
        sc.useDelimiter("@ARTICLE");  //To uniquely identify each article as each article starts with @ARTICLE.
        try {
            //Creates temporary buffer file to store each article data.
            bufferFileWriter = new PrintWriter(new FileOutputStream("Output\\bufferFile.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //First while loop, dedicated for each article within the same file.
        while (sc.hasNext()) {
            String articleJsonRead = sc.next();
            articleCount++;  //Article count is incremented to track number of articles in the single .bib file.
            bufferFileWriter.println(articleJsonRead);
            bufferFileWriter.flush();  // PrintWriter buffer is flushed, so that every time it stores new article data.
            try {
                //Reads the data available in buffer file.
                bufferScanner = new Scanner(new FileInputStream("E:\\CU One Drive\\OneDrive - Concordia University - Canada\\Fall 2021\\PPS\\Assignments\\Assignment 2\\Output\\bufferFile.txt"));

                //Second while loop, dedicated for each line within the same article.
                while (bufferScanner.hasNextLine()) {
                    //Reads the line from buffer file.
                    String lineRead = bufferScanner.nextLine().trim();
                    //System.out.println(lineRead);
                    //Checks whether the line contains author field.
                    if (lineRead.contains(author)) {
                        //Check for an empty field.
                        checkEmpty(lineRead, author, bufferScanner, count, articleCount);
                        //Fetches author names joined using "and" string.
                        l_author = fetchRequiredInformationFromLine(lineRead, 8);
                        //Array of author names.
                        l_authorNamesArray = parseAuthors(l_author);
                    }

                    //Checks whether the line contains journal field
                    else if (lineRead.contains(journal)) {
                        //Check for an empty field.
                        checkEmpty(lineRead, journal, bufferScanner, count, articleCount);
                        //Reads journal name.
                        l_journal = fetchRequiredInformationFromLine(lineRead, 9);
                    }

                    //Checks whether the line contains title field
                    else if (lineRead.contains(title)) {
                        //Check for an empty field.
                        checkEmpty(lineRead, title, bufferScanner, count, articleCount);
                        //Reads title of the article.
                        l_title = fetchRequiredInformationFromLine(lineRead, 7);
                    }

                    //Checks whether the line contains year field
                    else if (lineRead.contains(year)) {
                        //Check for an empty field.
                        checkEmpty(lineRead, year, bufferScanner, count, articleCount);
                        //Reads publication year of the article.
                        l_year = fetchRequiredInformationFromLine(lineRead, 6);
                    }

                    //Checks whether the line contains volume field
                    else if (lineRead.contains(volume)) {
                        //Check for an empty field.
                        checkEmpty(lineRead, volume, bufferScanner, count, articleCount);
                        //Reads volume information of the article.
                        l_volume = fetchRequiredInformationFromLine(lineRead, 8);
                    }

                    //Checks whether the line contains number field
                    else if (lineRead.contains(number)) {
                        //Check for an empty field.
                        checkEmpty(lineRead, number, bufferScanner, count, articleCount);
                        //Reads number field of the article.
                        l_number = fetchRequiredInformationFromLine(lineRead, 8);
                    }

                    //Checks whether the line contains pages field
                    else if (lineRead.contains(pages)) {
                        //Check for an empty field.
                        checkEmpty(lineRead, pages, bufferScanner, count, articleCount);
                        //Reads pages field of the article.
                        l_pages = fetchRequiredInformationFromLine(lineRead, 7);
                    }

                    //Checks whether the line contains keywords field
                    else if (lineRead.contains(keywords)) {
                        //Check for an empty field.
                        checkEmpty(lineRead, keywords, bufferScanner, count, articleCount);
                        //Reads keywords field of the article.
                        l_keywords = fetchRequiredInformationFromLine(lineRead, 10);
                    }

                    //Checks whether the line contains doi field
                    else if (lineRead.contains(doi)) {
                        //Check for an empty field.
                        checkEmpty(lineRead, doi, bufferScanner, count, articleCount);
                        //Reads doi field of the article.
                        l_doi = fetchRequiredInformationFromLine(lineRead, 5);
                    }

                    //Checks whether the line contains ISSN field
                    else if (lineRead.contains(issn)) {
                        //Check for an empty field.
                        checkEmpty(lineRead, issn, bufferScanner, count, articleCount);
                        //Reads ISSN field of the article.
                        l_issn = fetchRequiredInformationFromLine(lineRead, 6);
                    }

                    //Checks whether the line contains Month field
                    else if (lineRead.contains(month)) {
                        //Check for an empty field.
                        checkEmpty(lineRead, month, bufferScanner, count, articleCount);
                        //Reads Month field of the article.
                        l_month = fetchRequiredInformationFromLine(lineRead, 7);
                    }
                }
                //Checks if all the variable have a data stored within. If so, then appends them in IEEE, ACM and NJ format.
                if (l_author.length() > 0 && l_journal.length() > 0 && l_title.length() > 0 && l_year.length() > 0 && l_volume.length() > 0 && l_number.length() > 0 && l_pages.length() > 0 && l_keywords.length() > 0 && l_doi.length() > 0 && l_issn.length() > 0 && l_month.length() > 0) {
                    IEEE = String.join(",", l_authorNamesArray) + ". \"" + l_title + "\", " + l_journal + ", vol. " + l_volume + ", no. " + l_number + ", p. " + l_pages + ", " + l_month + " " + l_year + ".";
                    ACM = "[" + articleCount + "]" + l_authorNamesArray[0] + " et al. " + l_year + ". " + l_title + ". " + l_journal + ". " + l_volume + ", " + l_number + " (" + l_year + "), " + l_pages + ". " + "DOI:https://doi.org/" + l_doi + ".";
                    NJ = String.join(" &", l_authorNamesArray) + ". " + l_title + ". " + l_journal + ". " + l_volume + ", " + l_pages + "(" + l_year + ").";
                    IEEEWriter.println(IEEE);
                    IEEEWriter.println();
                    ACMWriter.println(ACM);
                    ACMWriter.println();
                    NJWriter.println(NJ);
                    NJWriter.println();
                    bufferScanner.close();
                }
            } catch (FileInvalidException e) {
                System.out.println(e.getMessage());
                IEEEWriter.close();
                ACMWriter.close();
                NJWriter.close();
                //Deletes all the invalid files.
                IEEEFile.delete();
                ACMFile.delete();
                NJFile.delete();
                invalidFiles++;
                break;
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        IEEEWriter.close();
        ACMWriter.close();
        NJWriter.close();
    }

    /**
     * Checks empty field in the .bib file.
     *
     * @param p_line         Line read from the buffer file.
     * @param p_fieldName    Name of the field.
     * @param p_scanner      Scanner class object to read the file.
     * @param p_count        Count for the file number.
     * @param p_articleCount Count for the article.
     * @throws FileInvalidException throws if file has empty field.
     */
    public static void checkEmpty(String p_line, String p_fieldName, Scanner p_scanner, int p_count, int p_articleCount) throws FileInvalidException {
        if (p_line.contains("{}")) {
            p_scanner.close();
            throw new FileInvalidException("Error: Detected Empty Field!\n ======================================\n\n"
                    + "Problem detected with input file: Latex" + p_count + ".bib\n"
                    + "File is invalid: Field " + p_fieldName + " is empty in article number " + p_articleCount + ". "
                    + "Processing stopped at this point. "
                    + "Other empty fields may be present as well!\n");
        }
    }

    /**
     * Returns the string from the line passed in the argument stating from the mentioned index.
     *
     * @param p_lineRead Line from which data is required to be extracted.
     * @param p_index    Index in the line.
     * @return String with required data.
     */
    public static String fetchRequiredInformationFromLine(String p_lineRead, int p_index) {
        return p_lineRead.substring(p_index, p_lineRead.length() - 2);
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

    /**
     * Reads the data available in the file.
     *
     * @param reader BufferedReader class object to read the file.
     */
    public static void readFile(BufferedReader reader) throws IOException, FileNotFoundException {
        String line;
        System.out.println("Please enter the name of one of the files that you need to review:");
        String fileToReview = kb.nextLine();
        reader = new BufferedReader(new FileReader("Output\\" + fileToReview));
        System.out.println("Here are the contents of the successfully created Jason File: " + fileToReview);
        line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            line = reader.readLine();
        }
        System.out.println("Goodbye! Hope you have enjoyed creating the needed files using BibCreator.");
    }

    /**
     * Main method handles all the operations of the application.
     *
     * @param args
     */
    public static void main(String args[]) {

        Scanner[] sc = new Scanner[fileCount];
        PrintWriter ACMWriter = null;
        PrintWriter IEEEWriter = null;
        PrintWriter NJWriter = null;
        int numberOfFiles = 0;

        System.out.println("Welcome to BibCreator\n");
        File file = new File("E:\\CU One Drive\\OneDrive - Concordia University - Canada\\Fall 2021\\PPS\\Assignments\\Assignment 2\\Output");
        while (numberOfFiles < fileCount) {
            try {
                sc[numberOfFiles] = new Scanner(new FileInputStream("Latex" + (numberOfFiles + 1) + ".bib"));
                numberOfFiles++;
            } catch (FileNotFoundException e) {
                System.out.println("Could not open input file Latex" + (numberOfFiles + 1) + ".bib for reading.\n\n"
                        + "Please check if file exists! Program will terminate after closing any opened files.");
                for (int i = 0; i < numberOfFiles; i++) {
                    sc[i].close();
                }
                break;
            }
        }

        if (numberOfFiles == fileCount) {
            for (int i = 0; i < fileCount; i++) {
                File IEEEFile = new File("Output\\IEEE" + (i + 1) + ".json");
                File ACMFile = new File("Output\\ACM" + (i + 1) + ".json");
                File NJFile = new File("Output\\NJ" + (i + 1) + ".json");

                try {
                    IEEEWriter = new PrintWriter(new FileOutputStream(IEEEFile));
                    ACMWriter = new PrintWriter(new FileOutputStream(ACMFile));
                    NJWriter = new PrintWriter(new FileOutputStream(NJFile));
                    processFilesForValidation(sc[i], IEEEWriter, ACMWriter, NJWriter, IEEEFile, ACMFile, NJFile, i + 1);
                    sc[i].close();
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("\nA total of " + invalidFiles + " invalid File(s) were discovered. " + (fileCount - invalidFiles) + " File(s) have been created out of valid Files\n\n");

            BufferedReader reader = null;
            try {
                readFile(reader);
            } catch (FileNotFoundException e) {
                System.out.println("Could not open input file. File does not exist; possibly it could not be created!\n");
                System.out.println("However, you will be allowed another chance to enter another file name.");
                //System.out.println("Please enter the name of one of the files that you need to review:");
                try {
                    readFile(reader);
                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("Could not open input file again! Either file does not exist or could not be created.");
                    System.out.println("Sorry! I am unable to display your desired files! Program will exit!");
                    System.exit(0);
                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}