package ToDoList.controllers;

import ToDoList.models.ToDo;
import ToDoList.models.User;
import ToDoList.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo/v1/")
@Api(value = "ToDo API")
public class TodoRestController {

    @Autowired
    TodoService todoService;

    @GetMapping("mytodos")
    @ApiOperation(value = "Получение списка всех дел.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
    })
    public ResponseEntity<List<ToDo>> getUserTodos() {
        return ResponseEntity.ok(todoService.findTodosByUser(getAuthenticatedUser()));
    }

    @GetMapping("mytodos/{todoId}")
    @ApiOperation(value = "Получение дела по id.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Дело не найдено")
    })
    public ResponseEntity<ToDo> getTodoById(@PathVariable Long todoId) {
        return todoService.findTodosByUser(getAuthenticatedUser()).stream()
                .filter(todo -> todo.getId().equals(todoId)).findFirst()
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("create")
    @ApiOperation(value = "Создание дела.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Дело не создано.")
    })
    public ResponseEntity<ToDo> createNewTodo(@RequestBody ToDo newTodo) {
        if (newTodo != null) {
            todoService.createNewTodo(getAuthenticatedUser(), newTodo);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("update/{todoId}")
    @ApiOperation(value = "Редактирование дела.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Дело не найдено.")
    })
    public ResponseEntity<ToDo> update(@PathVariable Long todoId, @RequestBody ToDo newTodo) {
        if (todoId != null && newTodo != null) {
            newTodo.setUser(getAuthenticatedUser());
            todoService.updateToDo(todoId, newTodo);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{todoId}")
    @ApiOperation(value = "Удаление дела.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Дело не найдено.")
    })
    public ResponseEntity<ToDo> delete(@PathVariable Long todoId) {
        if (todoId != null) {
            todoService.deleteTodo(todoId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
