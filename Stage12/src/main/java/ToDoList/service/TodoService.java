package ToDoList.service;

import ToDoList.models.ToDo;
import ToDoList.models.User;
import ToDoList.repositories.ToDoRepository;
import ToDoList.service.exceptions.ToDoNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ToDoList.service.ServiceUtils.getAuthenticatedUser;

/**
 * Класс TodoService.
 * Сервисный слой для ToDo. Реализация бизнес логики.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@Slf4j
@Service
public class TodoService {

    private final ToDoRepository toDoRepository;
    private final Messages messages;

    @Autowired
    public TodoService(ToDoRepository toDoRepository, Messages messages) {
        this.toDoRepository = toDoRepository;
        this.messages = messages;
    }

    /**
     * Метод findTodoById.
     * Возврат дела по Id.
     *
     * @param todoId Id дела.
     * @return объект класса ToDo.
     * @throws ToDoNotFoundException исключение если дело не найдено.
     */

    public ToDo findTodoById(Long todoId) throws ToDoNotFoundException {
        return toDoRepository.findById(todoId)
                .orElseThrow(() -> new ToDoNotFoundException(messages.getMessage("todo.notfound")));
    }

    /**
     * Метод createNewTodo.
     * Создание нового дела для авторизовавшегося пользователя.
     *
     * @param user            пользователь
     * @param todoFromRequest дело, полученное из запроса.
     */
    public void createNewTodo(User user, ToDo todoFromRequest) {
        todoFromRequest.setUser(user);
        toDoRepository.save(todoFromRequest);
        log.info(messages.getMessage("todo.create"));
    }

    /**
     * Метод updateToDo.
     * Обновление дела.
     *
     * @param todoId      Id дела.
     * @param updatedTodo обновленное дело.
     * @return true если дело обновлено.
     */
    public boolean updateToDo(Long todoId, ToDo updatedTodo) {
        try {
            if (findTodoById(todoId) != null) {
                updatedTodo.setId(todoId);
                updatedTodo.setUser(getAuthenticatedUser());
                toDoRepository.save(updatedTodo);
                log.info(messages.getMessage("todo.updated"));
                return true;
            }
        } catch (ToDoNotFoundException e) {
            log.warn(e.getMessage());
        }
        return false;
    }

    /**
     * Метод deleteTodo.
     * Удаление пользователя по id.
     *
     * @param todoId Id пользователя.
     * @return true если пользователь удален.
     */
    public boolean deleteTodo(Long todoId) {
        try {
            toDoRepository.delete(findTodoById(todoId));
            log.info(messages.getMessage("todo.deleted"));
            return true;
        } catch (ToDoNotFoundException e) {
            e.getMessage();
        }
        return false;
    }

}
