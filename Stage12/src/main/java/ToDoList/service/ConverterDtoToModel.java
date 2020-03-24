package ToDoList.service;

import ToDoList.dto.TodoRequestDTO;
import ToDoList.models.ToDo;

/**
 * Класс ConverterDtoToModel.
 * Конвертер тела запроса в сущность.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class ConverterDtoToModel {

    public static ToDo convert(TodoRequestDTO todoRequestDTO) {
        return ToDo.builder()
                .id(todoRequestDTO.getId())
                .name(todoRequestDTO.getName())
                .dateStart(todoRequestDTO.getDateStart())
                .dateEnd(todoRequestDTO.getDateEnd())
                .description(todoRequestDTO.getDescription())
                .build();
    }
}
