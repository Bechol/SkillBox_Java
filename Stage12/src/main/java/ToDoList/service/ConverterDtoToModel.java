package ToDoList.service;

import ToDoList.dto.TodoRequestDTO;
import ToDoList.models.ToDo;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс ConverterDtoToModel.
 * Конвертер тела запроса в сущность.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Slf4j
public class ConverterDtoToModel {

    public static ToDo convert(TodoRequestDTO todoRequestDTO) {
        log.info("Convert: {}", todoRequestDTO);
        return ToDo.builder()
                .id(todoRequestDTO.getId())
                .name(todoRequestDTO.getName())
                .dateStart(todoRequestDTO.getDateStart())
                .dateEnd(todoRequestDTO.getDateEnd())
                .description(todoRequestDTO.getDescription())
                .build();
    }
}
