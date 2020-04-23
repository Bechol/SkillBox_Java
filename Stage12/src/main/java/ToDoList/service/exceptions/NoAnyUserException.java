package ToDoList.service.exceptions;

public class NoAnyUserException extends NullPointerException {

    public NoAnyUserException(String message) {
        super(message);
    }
}
