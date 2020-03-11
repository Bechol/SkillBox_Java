package ToDoList.controllers;

import ToDoList.models.Storage;
import ToDoList.models.ToDo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/todos/{id}")
    public ResponseEntity get(@PathVariable int id) {
        ToDo toDo = Storage.getToDo(id);
        if (toDo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(toDo, HttpStatus.OK);
    }
}
