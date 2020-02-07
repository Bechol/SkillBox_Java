package SkillBoxDB;

import SkillBoxDB.Entities.*;
import org.hibernate.Session;

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

        String allPurchasesHQL = "From " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchaseLists = session.createQuery(allPurchasesHQL).getResultList();

        purchaseLists.forEach(s -> {
            printPurchaseData(s);
            session.save(new LinkedPurchaseList(
                    new LinkedPurchaseListPK(s.getStudent().getId(), s.getCourse().getId()),
                    s.getStudent(),
                    s.getCourse(),
                    s.getStudent().getName(),
                    s.getCourse().getName(),
                    s.getPrice(),
                    s.getSubscriptionDate()
            ));
        });


        session.close();
    }

    private static void printPurchaseData(PurchaseList purchase) {
        System.out.printf("student_id: %s%ncourse_id: %s%nstudent_name: %s%ncourse_name: %s%nprice: %s%n" +
                "subscription_date:  %s%n-----------------------------------------%n",
                purchase.getStudent().getId(),
                purchase.getCourse().getId(),
                purchase.getStudent().getName(),
                purchase.getCourse().getName(),
                purchase.getPrice(),
                purchase.getSubscriptionDate()
        );
    }
}
