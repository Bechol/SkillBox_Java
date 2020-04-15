package ToDoList.service;

import ToDoList.models.ToDo;
import ToDoList.models.User;
import ToDoList.repositories.ToDoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class TodoService {

    @Autowired
    ToDoRepository toDoRepository;

    public ToDo findTodoById(Long todoId) throws NoSuchElementException {
        return toDoRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("ToDo id:" + todoId + " not found"));
    }

    public List<ToDo> findTodosByUser(User authenticatedUser) {
        return new ArrayList<>(authenticatedUser.getTodos());
    }

    public void createNewTodo(User user, ToDo todoFromRequest) {
        todoFromRequest.setUser(user);
        toDoRepository.save(todoFromRequest);
        log.info("ToDo [{}] created.", todoFromRequest.getName());
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
