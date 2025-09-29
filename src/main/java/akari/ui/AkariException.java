package akari.ui;

/**
 * Signals an error caused within the program.
 */
public class AkariException extends Exception {
    public AkariException() {
    }

    public AkariException(String message) {
        super(message);
    }
}
