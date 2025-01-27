package de.ssherlock.global.transport;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents an Error DTO.
 *
 * @author Leon Höfling
 */
public class Error implements Serializable {

    /**
     * Serial Version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The message to display.
     */
    private String message;


    /**
     * The exception that was thrown.
     */
    private Exception exception;

    /**
     * Instantiates a new Error.
     */
    public Error() {}

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * Gets exception.
     *
     * @return the exception
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Sets exception.
     *
     * @param exception the exception
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }
}
