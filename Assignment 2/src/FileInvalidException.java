/**
 * Assignment 2
 *
 * @author CHARIT
 * Written by: Charit Patel, id: 40160658
 *
 * Represents the user defined exception class to handle the exception occured when the file is invalid.
 */
public class FileInvalidException extends Exception {

    private String message;

    /**
     * Default constructor
     */
    public FileInvalidException() {
        message = "Error: Input file cannot be parsed due to missing information (i.e. month={}, title={}, etc.)";
    }

    /**
     * Parameterized constructor to set the error message.
     *
     * @param s Error message.
     */
    public FileInvalidException(String s) {
        this.message = s;
    }

    /**
     * Overrides the getMessage() method of an Exception class.
     * It returns the string representing an error message.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return message;
    }
}