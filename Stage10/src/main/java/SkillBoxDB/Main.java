package SkillBoxDB;

import SkillBoxDB.Entities.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Домашняя работа 10.5.3.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-info.java
 */
public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        //Домашняя работа 10.3.2.
        Course course = session.get(Course.class, 1);
        System.out.println(course.getName() + " " + course.getType());

        course.getStudents().forEach(s -> System.out.println(s.getName()));
        Student student = session.get(Student.class, 3);
        student.getCourses().forEach(c -> System.out.println(c.getName()));

        String allStudentsHQL = "From " + Student.class.getSimpleName();
        List<Student> stList = session.createQuery(allStudentsHQL).getResultList();
        List<PurchaseListPK> pkList = new ArrayList<>();
        List<LinkedPurchaseListPK> lpkList = new ArrayList<>();
        stList.forEach(s -> {
            s.getCourses().forEach(c -> pkList.add(new PurchaseListPK(s.getName(), c.getName())));
        });

        pkList.forEach(ppk -> {
            System.out.println("StudentId: " + session.get(PurchaseList.class, ppk).getStudent().getId() +
                    ". CourseId: " + session.get(PurchaseList.class, ppk).getCourse().getId());
        });



        session.close();
    }
}
