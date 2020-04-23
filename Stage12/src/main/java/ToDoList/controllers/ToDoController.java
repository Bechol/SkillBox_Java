package ToDoList.controllers;

import ToDoList.models.ToDo;
import ToDoList.service.TodoService;
import ToDoList.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static ToDoList.service.ServiceUtils.getAuthenticatedUser;

@Controller
@Slf4j
public class ToDoController {

    @Autowired
    TodoService todoService;

    @Autowired
    UserService userService;

    /**
     * Метод getIndexView.
     * Добавление аттрибутов и вывод страницы индекс.
     *
     * @param model модель для добавления аттрибутов.
     * @return шаблон index.
     * @request GET http://localhost:8080/
     */
    @GetMapping
    public String getIndexView(Model model) {
        model.addAttribute("user", userService.findUserById(getAuthenticatedUser().getId()));
        model.addAttribute("newTodo", new ToDo());
        return "index";
    }

    /**
     * Метод createNewToDo.
     * POST запрос на создание нового дела для авторизовавшегося пользователя.
     *
     * @param newTodo объект нового дела.
     * @return переход на стартовую страницу.
     * @request POST http://localhost:8080/todo/create
     */
    @PostMapping("/todo/create")
    public String createNewToDo(@ModelAttribute ToDo newTodo) {
        todoService.createNewTodo(getAuthenticatedUser(), newTodo);
        return "redirect:/";
    }

    /**
     * Метод getEditTodoView.
     * GET запрос на получение дела по Id.
     *
     * @param todoId Id дела.
     * @param model  модель для добавления аттрибутов.
     * @return шаблон edittodo.
     * @request GET http://localhost:8080/todo/{todoId}
     */
    @GetMapping("/todo/{todoId}")
    public String getEditTodoView(@PathVariable("todoId") Long todoId, Model model) {
        model.addAttribute("todo", todoService.findTodoById(todoId));
        return "edittodo";
    }

    /**
     * Метод updateTodoById.
     * POST запрос на изменение дела по Id.
     *
     * @param todoId Id дела.
     * @param todo   дело.
     * @return переход на стратовую страницу.
     * @request POST http://localhost:8080/todo/update/{todoId}
     */
    @PostMapping("/todo/update/{todoId}")
    public String updateTodoById(@PathVariable("todoId") Long todoId, ToDo todo) {
        return todoService.updateToDo(todoId, todo) ? "redirect:/" : "redirect:/";
    }

    /**
     * Метод deleteTodoById.
     * Запрос на удаление дела по Id.
     *
     * @param todoId Id пользователя.
     * @return переход на стратовую страницу.
     * @request GET http://localhost:8080/todo/delete/{todoId}
     */
    @GetMapping("/todo/delete/{todoId}")
    public String deleteTodoById(@PathVariable("todoId") Long todoId) {
        todoService.deleteTodo(todoId);
        return "redirect:/";
    }
}
