package by.htp.jd2.maksimkosmachev.testapp.dao.exception;

public class SuchUserNotExistException extends Throwable {
    public SuchUserNotExistException() {
    }

    public SuchUserNotExistException(String message) {
        super(message);
    }

    public SuchUserNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
