package ToDoList.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ToDo {

    private int id;
    private String name;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String description;
}
