package ToDoList.service.exceptions;

import java.util.NoSuchElementException;

public class ToDoNotFoundException extends NoSuchElementException {

    public ToDoNotFoundException(String message) {
        super(message);
    }
}
