package SkillBoxDB.Entities;


import SkillBoxDB.CourseType;
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
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int duration;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private int students_count;
    @Getter
    @Setter
    private int price;
    @Getter
    @Setter
    private float price_per_hour;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    @Getter
    @Setter
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subscriptions",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    @Getter
    @Setter
    private List<Student> students;
}
