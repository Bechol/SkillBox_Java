package ToDoList.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private static int currentId = 1;
    private static HashMap<Integer, ToDo> todos = new HashMap<>();

    public static List<ToDo> getAllTodos() {
        ArrayList<ToDo> todoList = new ArrayList<>();
        todoList.addAll(todos.values());
        return todoList;
    }

    public static int addTodo(ToDo todo) {
        int id = currentId++;
        todo.setId(id);
        todos.put(id, todo);
        return id;
    }

    public static ToDo getToDo(int todoId) {
        if (todos.containsKey(todoId)) {
            return todos.get(todoId);
        }
        return null;
    }
}
