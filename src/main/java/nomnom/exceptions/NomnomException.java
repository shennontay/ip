package nomnom.exceptions;

/**
 * Represents an exception specific to the Nomnom application.
 * Used to signal errors related to tasks, storage, or command handling.
 */
public class NomnomException extends Exception {
    /**
     * Creates a new NomnomException with the given message.
     *
     * @param message the error message to show
     */
    public NomnomException(String message) {
        super(message);
    }
}
