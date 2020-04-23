package ToDoList.service;

import ToDoList.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Класс ServiceUtils.
 * Утилитный класс для сервисного слоя.
 * @author Oleg Bech.
 * @email oleg071984@gmail.com.
 */
public class ServiceUtils {

    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
