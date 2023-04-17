package org.example.exception;

/**
 * The class ManageException indicates that an unknown command has arrived.
 */

public class ManageException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public ManageException(String message) {
        super(message);
    }
}
