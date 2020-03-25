package ToDoList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * Класс TodoRequestDTO.
 * Реализация тела дела.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDTO {

    @JsonIgnore
    private Long id;

    /**
     * Наименование дела.
     */
    private String name;
    /**
     * Дата начала.
     */
    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private Date dateStart;
    /**
     * Дата завершения.
     */
    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private Date dateEnd;
    /**
     * Описание.
     */
    private String description;

}
