package CourseInfo;

import CourseInfo.Entities.Course;
import org.hibernate.Session;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

/**
 * Домашняя работа 10.3.2.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-info.java
 */
public class Main {

    private static final int COURSE_ID = 1;

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        System.out.println(session.get(Course.class, COURSE_ID));

        session.close();
    }
}
