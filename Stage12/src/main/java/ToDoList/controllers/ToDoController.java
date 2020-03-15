package ToDoList.controllers;

import ToDoList.models.Storage;
import ToDoList.models.ToDo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    @RequestMapping("/todos/")
    public List<ToDo> list() {
        return Storage.getAllTodos();
    }

    @PostMapping("/todos/")
    public int add(ToDo toDo) {
        return Storage.addTodo(toDo);
    }

    @GetMapping("/todos/{id}")
    ResponseEntity<ToDo> get(@PathVariable("id") int id) {
        ToDo toDo = Storage.getToDo(id);
        return toDo != null ? ResponseEntity.ok(toDo) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/todos/{id}")
    synchronized ResponseEntity<Void> delete(@PathVariable("id") int id) {
        return Storage.deleteToDo(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PatchMapping("/todos/{id}")
    synchronized ResponseEntity<Void> update(@PathVariable("id") int id, ToDo toDo) {
        return Storage.patchToDo(id, toDo) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/todos/{id}")
    synchronized ResponseEntity<Void> replace(@PathVariable("id") int id, ToDo toDo) {
        return Storage.replaceToDo(id, toDo) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
