package SkillBoxDB;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Класс HibernateUtil.
 * Создание SessionFactory для доступа к базе данных.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration();
            sessionFactory = config.configure("SkillBoxDB.cfg.xml").buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Error in creating SessionFactory object." + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
