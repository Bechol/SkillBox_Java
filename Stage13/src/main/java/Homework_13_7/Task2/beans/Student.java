package Homework_13_7.Task2.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс Student.
 * Реализация студента.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@Data
@NoArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String courses;
    private List<Course> courseList = new ArrayList<>();

    public Student(String name, int age, String courses) {
        this.name = name;
        this.age = age;
        this.courses = courses;
    }

    /**
     * Метод createDoc.
     * Создание Bson документа из бина.
     * @return Bson документ.
     */
    public Document createDoc() {
        Document document = new Document();
        document.put("Name", this.name);
        document.put("Age", this.age);
        document.put("Courses", this.courseList.stream()
                .map(course -> new Document().append("course:", course.getName())).collect(Collectors.toList()));
        return document;
    }
}
