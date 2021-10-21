public class FullStackException extends Exception {

    String message;

    public FullStackException(String m) {
        message = m;
    }

    public String getMessage() {
        return message;
    }
}
