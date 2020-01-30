package SkillBoxDB.Entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс Student.
 * Описание сущности студента.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @Basic
    private Timestamp registration_date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subscriptions",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private List<Course> courses;
}
