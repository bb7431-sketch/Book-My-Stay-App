/**
 * InvalidBookingException is a custom exception used to represent 
 * domain-specific validation failures in the Hotel Booking System.
 * 
 * @author Book My Stay Team
 * @version 9.0
 */
public class InvalidBookingException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param message the detail message
     */
    public InvalidBookingException(String message) {
        super(message);
    }
}
