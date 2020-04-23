package ToDoList.controllers;

import ToDoList.models.User;
import ToDoList.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Класс RegistrationController.
 * Контроллер. Регистрация новых пользователей со страницы "/login".
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Slf4j
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод getRegistrationView.
     * Запрос на открытие страницы регистрации.
     *
     * @param model модель для добавления аттрибутов.
     * @return шаблон registration.
     * @request GET http://localhost:8080/registration.
     */
    @GetMapping
    public String getRegistrationView(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }


    /**
     * Метод registrateNewUser.
     * POST запрос на добавление нового пользователя.
     *
     * @param newUser объект нового пользователя с параметрвми.
     * @return переход на главную страницу.
     * @request POST http://localhost:8080/registration.
     */
    @PostMapping
    public String registrateNewUser(@ModelAttribute User newUser) {
        return userService.registrateUser(newUser) ? "redirect:/" : "redirect:/";
    }

}
