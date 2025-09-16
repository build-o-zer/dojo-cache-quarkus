package org.buildozers;

/**
 * Exception thrown when business logic processing is interrupted.
 */
public class BusinessLogicInterruptedException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Business logic processing was interrupted unexpectedly. " +
            "This may be due to thread interruption, system shutdown, or timeout. " +
            "Please check the cause for more details or retry the operation.";

    public BusinessLogicInterruptedException() {
        super(DEFAULT_MESSAGE);
    }

    public BusinessLogicInterruptedException(String message) {
        super(DEFAULT_MESSAGE + " Additional details: " + message);
    }

    public BusinessLogicInterruptedException(String message, Throwable cause) {
        super(DEFAULT_MESSAGE + " Additional details: " + message, cause);
    }

    public BusinessLogicInterruptedException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
