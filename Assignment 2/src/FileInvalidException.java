public class FileInvalidException extends Exception {

    private String message;


    // constructor with passed param.
    public FileInvalidException(String s) {

        this.message = s;

    }

    // default constructor
    public FileInvalidException() {
        message = "Error: Input file cannot be parsed due to missing information (i.e. month={}, title={}, etc.)";
    }

    public String getMessage() {
        return message;
    }
}