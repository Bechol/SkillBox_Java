package ToDoList.controllers;

import ToDoList.models.ToDo;
import ToDoList.models.User;
import ToDoList.repositories.ToDoRepository;
import ToDoList.repositories.UserRepository;
import ToDoList.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class MainController {

    @Autowired
    TodoService todoService;

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String index(Model model) {
        User user = userRepository.findById(getAuthenticatedUser().getId())
                .orElseThrow(() -> new NoSuchElementException("USer not found."));
        model.addAttribute("todos", user.getTodos());
        model.addAttribute("username", user.getUsername());
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
