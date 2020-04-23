package ToDoList.controllers;

import ToDoList.models.User;
import ToDoList.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Класс AdminController.
 * Контроллер. Реализация функционала для администратора.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод getUsers.
     * Запрос для получения списка поьльзователей.
     *
     * @param model модель для добавления аттрибутов.
     * @return шаблон appusers.
     * @request GET http://localhost:8080/admin/users.
     */
    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("user", new User());
        model.addAttribute("newUser", new User());
        return "appusers";
    }

    /**
     * Метод getUserEditView.
     * Запрос для получения пользователя по ID.
     *
     * @param userId Id пользователя.
     * @param model  модель для добавления аттрибутов.
     * @return шаблон userprofile.
     * @request GET http://localhost:8080/admin/user/{userId}.
     */
    @GetMapping("/user/{userId}")
    public String getUserEditView(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        return "userprofile";
    }

    /**
     * Метод createUser.
     * Запрос для создания пользователя.
     *
     * @param newUser объект пользователя, параметры которого заданы на web форме.
     * @return redirect на страницу с пользователями.
     * @request POST http://localhost:8080/admin/user/create.
     */
    @PostMapping("/user/create")
    public String createUser(@ModelAttribute User newUser) {
        return userService.registrateUserByAdmin(newUser) ? "redirect:/admin/users" : "redirect:/admin/users";
    }

    /**
     * Метод deleteUserById.
     * Запрос для удаления пользователя.
     * @request GET http://localhost:8080/admin/user/delete/{userId}.
     * @param userId Id пользователя.
     * @return redirect на страницу с пользователями.
     */
    @GetMapping("/user/delete/{userId}")
    public String deleteUserById(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/users";
    }

    /**
     * Метод resetUserPassword.
     * Запрос для сброса пароля пользователя на стандартный.
     * @request GET http://localhost:8080/admin//user/password/reset/{userId}.
     * @param userId Id пользователя.
     * @return redirect на страницу с пользователями.
     */
    @GetMapping("/user/password/reset/{userId}")
    public String resetUserPassword(@PathVariable("userId") Long userId) {
        return userService.resetUserPassword(userId) ? "redirect:/admin/users" : "redirect:/admin/users";
    }
}
