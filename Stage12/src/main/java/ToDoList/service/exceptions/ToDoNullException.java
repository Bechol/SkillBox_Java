package ToDoList.service.exceptions;

public class ToDoNullException extends NullPointerException {
    public ToDoNullException(String msg) {
        super(msg);
    }
}
