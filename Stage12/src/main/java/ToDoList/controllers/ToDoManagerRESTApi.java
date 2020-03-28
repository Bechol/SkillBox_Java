package ToDoList.controllers;

import ToDoList.dto.TodoRequestDTO;
import ToDoList.models.ToDo;
import ToDoList.service.ConverterDtoToModel;
import ToDoList.service.TodoManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

/**
 * Класс ToDoManagerRESTApi.
 * Контроллер REST-API.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@RestController
@RequestMapping(value = "/api/v1/todo/")
@Api(value = "ToDo Manager", description = "Api для работы с делами")
@Slf4j
public class ToDoManagerRESTApi {

    private final TodoManagerService todoManagerService;

    @Autowired
    public ToDoManagerRESTApi(TodoManagerService todoManagerService) {
        this.todoManagerService = todoManagerService;
    }

    @PostMapping
    @ApiOperation(value = "Создание дела", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Дело успешно создано"),
    })
    public ResponseEntity<ToDo> addToDo(@RequestBody TodoRequestDTO todoRequestDTO) {
        ToDo toDo = ConverterDtoToModel.convert(todoRequestDTO);
        log.info("addToDo: {}", toDo);
        todoManagerService.addTodo(toDo);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "alltodos")
    @ApiOperation(value = "Список всех дел", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Список невозможно вывести"),
    })
    public ResponseEntity<List<ToDo>> getToDoList(
            @RequestParam(name = "page", required = false) @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false) @Min(1) Integer pageSize,
            @RequestParam(name = "sortBy", required = false) Map<String, Boolean> sortBy,
            @RequestParam(name = "filters", required = false) Map<String, Object> filters
    ) {
        if ((page == null || pageSize == null) && (sortBy == null || sortBy.isEmpty()) && (filters == null || filters.isEmpty())) {
            return ResponseEntity.ok(todoManagerService.getFullTodoList());
        } else {
            return ResponseEntity.ok(todoManagerService.getTodoFilteredList(page, pageSize, sortBy, filters));
        }
    }

    @GetMapping(value = "{todoId}")
    @ApiOperation(value = "Получение дела по id", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Дело успешно найдено"),
            @ApiResponse(code = 500, message = "Дело с таким id не существует"),
    })
    public ResponseEntity<ToDo> getToDoById(@PathVariable Long todoId) {
        if (todoId != null) {
            return ResponseEntity.ok(todoManagerService.getTodoById(todoId));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping(value = "{todoId}")
    @ApiOperation(value = "Обновление дела", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Дело успешно обновлено"),
            @ApiResponse(code = 500, message = "Дело с таким id не существует")
    })
    public ResponseEntity<ToDo> updatePoll(@PathVariable Long todoId, @RequestBody TodoRequestDTO todoRequestDTO) {
        ToDo toDo = ConverterDtoToModel.convert(todoRequestDTO);
        toDo.setId(todoId);
        return ResponseEntity.ok(todoManagerService.updateToDo(toDo));
    }

    @DeleteMapping(value = "{todoId}")
    @ApiOperation(value = "Удаление дела", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Дело успешно удален"),
            @ApiResponse(code = 500, message = "Дело с таким id не существует")
    })
    public ResponseEntity<Void> removePoll(@PathVariable Long todoId) {
        todoManagerService.removeToDo(todoId);
        return ResponseEntity.noContent().build();
    }
}
