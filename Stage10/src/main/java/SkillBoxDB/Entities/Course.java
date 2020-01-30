package SkillBoxDB.Entities;


import SkillBoxDB.Enums.CourseType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int duration;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;
    private String description;
    private int students_count;
    private int price;
    private float price_per_hour;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subscriptions",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private List<Student> students;
}
