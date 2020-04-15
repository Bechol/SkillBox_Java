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

@Builder
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

    public TodoRequestDTO() {
    }

    public TodoRequestDTO(Long id, String name, Date dateStart, Date dateEnd, String description) {
        this.id = id;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
