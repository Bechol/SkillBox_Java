package ToDoList.repositories;

import ToDoList.models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс ToDoRepository.
 * Репозиторий работы с таблицей todo.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>, JpaSpecificationExecutor<ToDo> {
}
