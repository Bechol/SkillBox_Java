package SkillBoxDB.Entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Класс Teacher.
 * Описание сущности учителя.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    List<Course> courseList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int salary;
    private int age;
}
