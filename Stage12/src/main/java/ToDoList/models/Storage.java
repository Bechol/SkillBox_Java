package ToDoList.models;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static ArrayList<ToDo> todos = new ArrayList<>();

    public static List<ToDo> getAllTodos() {
        return todos;
    }

    public static int addTodo(ToDo todo) {
        int id = todos.size() + 1;
        todo.setId(id);
        todos.add(todo);
        return id;
    }
}
