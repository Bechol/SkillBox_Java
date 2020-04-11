package ToDoList.service;

import ToDoList.models.ToDo;
import ToDoList.repositories.ToDoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class CaseService {

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    UserService userService;

    public ToDo findTodoById(Long todoId) throws NoSuchElementException {
        return toDoRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("ToDo id:" + todoId + " not found"));
    }

    public boolean createNewTodo(Long userId, ToDo todoFromRequest) {
        try {
            todoFromRequest.setUser(userService.findUserById(userId));
            toDoRepository.save(todoFromRequest);
            log.info("ToDo [{}] created for user id:{}", todoFromRequest.getName(), userId);
            return true;
        } catch (NoSuchElementException e) {
            log.warn(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateToDo(Long todoId, ToDo todoFromRequest) {
        try {
            ToDo updatedTodo = findTodoById(todoId);
            updatedTodo.setName(todoFromRequest.getName());
            updatedTodo.setDateStart(todoFromRequest.getDateStart());
            updatedTodo.setDateEnd(todoFromRequest.getDateEnd());
            updatedTodo.setDescription(todoFromRequest.getDescription());
            toDoRepository.save(updatedTodo);
            log.info("ToDo id:{} updated.", todoId);
            return true;
        } catch (NoSuchElementException e) {
            log.warn(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTodo(Long todoId) {
        try {
            toDoRepository.delete(findTodoById(todoId));
            log.info("ToDo id:{} deleted.", todoId);
            return true;
        } catch (NoSuchElementException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return false;
    }

}
