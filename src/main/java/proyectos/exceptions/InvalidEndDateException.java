package proyectos.exceptions;

public class InvalidEndDateException extends RuntimeException {
    public InvalidEndDateException(String message) {
        super(message);
    }
}
