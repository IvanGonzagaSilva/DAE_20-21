package exceptions;


public class MyEntityExistsException extends Exception {
    public MyEntityExistsException() {
    }

    public MyEntityExistsException(String message) {
        super(message);
    }

    public MyEntityExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyEntityExistsException(Throwable cause) {
        super(cause);
    }

    public MyEntityExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
