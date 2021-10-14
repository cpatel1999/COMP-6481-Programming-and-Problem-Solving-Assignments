import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BibCreator {

    public static int fileCount = 10;
    public static void main(String args[])
    {
        Scanner kb = new Scanner(System.in);
        Scanner sc = null;
        PrintWriter ACMWriter = null;
        PrintWriter IEEEWriter = null;
        PrintWriter NJWriter = null;
        int numberOfFiles = 0;

        System.out.println("Welcome to BibCreator");
        File file = new File("E:\\CU One Drive\\OneDrive - Concordia University - Canada\\Fall 2021\\PPS\\Assignments\\Assignment 2\\Output");
        while(numberOfFiles <= fileCount)
        {
            try {
                sc = new Scanner(new FileInputStream("Latex"+numberOfFiles+".bib"));

            } catch (FileNotFoundException e) {
                System.out.println("Could not open input file Latex"+numberOfFiles+".bib for reading. Please check if file exists! Program will terminate after closing any opened files.");
            }
            numberOfFiles++;
        }
    }

}
