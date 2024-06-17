package proyectos.exceptions;

public class CantCreateTaskOnInvalidProjectException extends RuntimeException {
    public CantCreateTaskOnInvalidProjectException(String message) {
        super(message);
    }
}
