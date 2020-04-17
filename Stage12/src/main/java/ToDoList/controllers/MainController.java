package ToDoList.controllers;

import ToDoList.models.ToDo;
import ToDoList.models.User;
import ToDoList.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    TodoService todoService;

    @GetMapping
    public String index(Model model) {
        List<ToDo> todos = todoService.findTodosByUser(getAuthenticatedUser());
        model.addAttribute("todos", todos);
        model.addAttribute("username", getAuthenticatedUser().getUsername());
        model.addAttribute("newTodo", new ToDo());
        return "index";
    }

    @PostMapping("/todo/create")
    public String create(@ModelAttribute ToDo newTodo) {
        todoService.createNewTodo(getAuthenticatedUser(), newTodo);
        return "redirect:/";
    }

    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
