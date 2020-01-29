package SkillBoxDB;

import SkillBoxDB.Entities.Course;
import SkillBoxDB.Entities.Student;
import org.hibernate.Session;

/**
 * Домашняя работа 10.5.3.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-info.java
 */
public class Main {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Course course = session.get(Course.class, 1);
        System.out.println(course.getName() + " " + course.getType());
        course.getStudents().forEach(s -> System.out.println(s.getName()));
        Student student = session.get(Student.class, 3);
        student.getCourses().forEach(c -> System.out.println(c.getName()));

        session.close();
    }
}
