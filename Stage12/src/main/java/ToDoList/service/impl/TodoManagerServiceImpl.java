package ToDoList.service.impl;

import ToDoList.models.ToDo;
import ToDoList.repositories.ToDoRepository;
import ToDoList.service.TodoManagerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class TodoManagerServiceImpl implements TodoManagerService {

    private final ToDoRepository toDoRepository;

    public TodoManagerServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public void addTodo(ToDo toDo) {

            toDoRepository.save(toDo);
    }

    @Override
    public List<ToDo> getTodoFilteredList(Integer page, Integer pageSize, Map<String, Boolean> sortBy, Map<String, Object> filters) {
        Sort sort = Sort.unsorted();

        for (Map.Entry<String, Boolean> entry : sortBy.entrySet()) {
            String neededField = ToDoServiceUtils.getField(entry.getKey());

            if (neededField == null) {
                continue;
            }
            if (entry.getValue()) {
                sort = sort.and(Sort.by(neededField).ascending());
            } else {
                sort = sort.and(Sort.by(neededField).descending());
            }
        }

        Specification<ToDo> specification = ToDoServiceUtils.getSpecifications(filters);

        return toDoRepository.findAll(specification, PageRequest.of(page, pageSize, sort)).getContent();
    }

    @Override
    public List<ToDo> getFullTodoList() {
        return IterableUtils.toList(toDoRepository.findAll());
    }


    @Override
    public ToDo getTodoById(long id) {
        Optional<ToDo> result = toDoRepository.findById(id);
        return result.orElseThrow(() -> new NoSuchElementException("ToDo not exist."));
    }

    @Override
    public ToDo updateToDo(ToDo newToDo) {
        toDoRepository.findById(newToDo.getId()).orElseThrow(() -> new NoSuchElementException("ToDo not exist."));
        toDoRepository.save(newToDo);
        return null;
    }

    @Override
    public void removeToDo(long id) {
        ToDo todo = toDoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ToDo not exist"));
        toDoRepository.delete(todo);
    }
}
