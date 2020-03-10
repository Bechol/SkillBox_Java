package ToDoList.controllers;

import ToDoList.models.ToDo;
import ToDoList.models.Storage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToDoController {

    @RequestMapping(value = "/todos/", method = RequestMethod.GET)
    public List<ToDo> list() {
        return Storage.getAllTodos();
    }

    @RequestMapping(value = "/todos/", method = RequestMethod.POST)
    public int add(ToDo toDo) {
        return Storage.addTodo(toDo);
    }
}
