package ToDoList.service;

import ToDoList.models.ToDo;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс TodoManagerService.
 * Сервис для работы с делами.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public interface TodoManagerService {

    /**
     * Добавить дело.
     * @param toDo дело.
     */
    void addTodo(ToDo toDo);

    /**
     * Возврат списка дел c учетом зданных фильтров.
     * @param page страница.
     * @param pageSize размер страницы.
     * @param sortBy параметры сортировки.
     * @param filters фильтры.
     * @return коллекция дел.
     */
    List<ToDo> getTodoFilteredList(Integer page, Integer pageSize, Map<String, Boolean> sortBy, Map<String, Object> filters);

    /**
     * Возврат списка дел.
     * @return коллекция дел.
     */
    List<ToDo> getFullTodoList();

    /**
     * Возврат дела по id.
     * @return дело.
     */
    ToDo getTodoById(long id);

    /**
     * Обновить дело по id.
     * @param id идентификатор.
     * @param newToDo дело с новыми параметрами.
     * @return обновленное дело.
     */
    ToDo updateToDo(ToDo newToDo);

    /**
     * Удалить дело по id;
     * @param id идентификатор.
     * @return true если дело удалено.
     */
    void removeToDo(long id);
}
