package ToDoList.controllers;

import ToDoList.models.ToDo;
import ToDoList.models.User;
import ToDoList.repositories.ToDoRepository;
import ToDoList.repositories.UserRepository;
import ToDoList.service.TodoService;
import ToDoList.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

@Controller
@Slf4j
public class MainController {

    @Autowired
    TodoService todoService;

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping
    public String index(Model model) {
        User user = userRepository.findById(getAuthenticatedUser().getId())
                .orElseThrow(() -> new NoSuchElementException("USer not found."));
        model.addAttribute("todos", user.getTodos());
        model.addAttribute("user", user);
        model.addAttribute("newTodo", new ToDo());
        return "index";
    }

    @PostMapping("/todo/create")
    public String create(@ModelAttribute ToDo newTodo) {
        todoService.createNewTodo(getAuthenticatedUser(), newTodo);
        return "redirect:/";
    }

    @GetMapping("/todo/{todoId}")
    public String getTodoById(@PathVariable("todoId") Long todoId, Model model) {
        ToDo todo = toDoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException("Todo not found"));
        log.info("Model attribute todoForEdit created. {} {}", todo.getId(), todo.getName());
        model.addAttribute("todo", todo);
        return "edittodo";
    }

    @PostMapping("/todo/update/{todoId}")
    public String updateTodoById(@PathVariable("todoId") Long todoId, ToDo todo, Model model) {
        todo.setId(todoId);
        todo.setUser(getAuthenticatedUser());
        toDoRepository.save(todo);
        return "redirect:/";
    }

    @GetMapping("/todo/delete/{todoId}")
    public String deleteTodoById(@PathVariable("todoId") Long todoId) {
        toDoRepository.deleteById(todoId);
        return "redirect:/";
    }

    @GetMapping("/user")
    public String getAuthenticatedUserPropertiesView(Model model) {
        User user = userRepository.findById(getAuthenticatedUser().getId())
                .orElseThrow(() -> new NoSuchElementException("Authenticated user not found."));
        log.info("Authenticated user: {}", user);
        model.addAttribute("user", user);
        return "editmyprofile";
    }

    @PostMapping("/user/update")
    public String updateAuthenticatedUserProfile(User user) {
        User authenticatedUser = userRepository.findById(getAuthenticatedUser().getId())
                .orElseThrow(() -> new NoSuchElementException("Authenticated user not found."));
        userService.updateUserProperties(authenticatedUser, user);
        userService.updateUserPassword(authenticatedUser, user);
        return "redirect:/";
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", new User());
        model.addAttribute("newUser", new User());
        return "appusers";
    }

    @GetMapping("/admin/user/{userId}")
    public String getUserEditView(@PathVariable("userId") Long userId, Model model) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NoSuchElementException("User id:" + userId + "not found."));
        model.addAttribute("user", user);
        return "userprofile";
    }

    @PostMapping("/admin/user/create")
    public String createUser(@ModelAttribute User newUser) {
        userService.saveUser(newUser);
        return "redirect:/";
    }

    @GetMapping("/admin/user/delete/{userId}")
    public String deleteUserById(@PathVariable("userId") Long userId) {
        userRepository.deleteById(userId);
        return "redirect:/admin/users";
    }

    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
