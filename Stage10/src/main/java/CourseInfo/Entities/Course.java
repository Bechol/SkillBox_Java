package CourseInfo.Entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Класс Course.
 * Описание сущности курса.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    private int id;
    private String name;
    private int duration;
    private String description;
    private int students_count;
    private int price;
    private float price_per_hour;
}
