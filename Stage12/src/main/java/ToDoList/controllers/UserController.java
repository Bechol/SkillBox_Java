package ToDoList.controllers;

import ToDoList.models.User;
import ToDoList.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static ToDoList.service.ServiceUtils.getAuthenticatedUser;

/**
 * Класс UserController.
 * Контроллер. Функционал для пользователя.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com.
 */
@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод getAuthenticatedUserPropertiesView.
     * GET запрос для изменения свойств пользователя.
     *
     * @param model модель для добавления данных.
     * @return шаблон editmyprofile.
     * @request GET http://localhost:8080/user
     */
    @GetMapping("/user")
    public String getAuthenticatedUserPropertiesView(Model model) {
        User user = userService.findUserById(getAuthenticatedUser().getId());
        log.info("Authenticated user: {}", user);
        model.addAttribute("user", user);
        return "editmyprofile";
    }

    /**
     * Метод updateAuthenticatedUserProfile.
     * Запрос для обновления свойств пользователя.
     *
     * @param user пользователь, параметры которого необходимо изменить.
     * @return переход на стратовую страницу.
     */
    @PostMapping("/user/update")
    public String updateAuthenticatedUserProfile(User user) {
        User authenticatedUser = userService.findUserById(getAuthenticatedUser().getId());
        userService.updateUserProperties(authenticatedUser, user);
        userService.changeUserPassword(authenticatedUser, user);
        return "redirect:/";
    }
}
