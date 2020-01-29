package CourseInfo.Entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
@Table(name = "courses", schema = "CourseInfo")
public class Course {
    @Id
    private int id;
    private String name;
    private int duration;
    private String description;
    @Column(name="students_count")
    private int studentsCount;
    private int price;
    @Column(name="price_per_hour")
    private float pricePerHour;
}
