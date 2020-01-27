package JDBCExperiments;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс DBHElper.
 * Реализация подключения к базе данных.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/skillbox?" +
            "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    /**
     * Метод Connection connect().
     * Для установки подключения к базе данных.
     *
     * @return экземпляр Connection.
     */
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException sqle) {
            return null;
        }
    }


}
