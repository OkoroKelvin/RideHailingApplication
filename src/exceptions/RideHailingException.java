package exceptions;

public class RideHailingException extends Exception {
    public RideHailingException() {
    }

    public RideHailingException(String message) {
        this(message, null);
    }

    public RideHailingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RideHailingException(Throwable cause) {
        super(cause);
    }
}