package JDBCExperiments;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Домашнее задание 10.2.3.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-info.java
 */
public class Main {
    /**
     * Создание соединения с базой данных.
     */
    private static final Connection connection = DBHelper.connect();
    /**
     * Строка SQL запроса к базе данных.
     */
    private static final String sqlQuery = "SELECT course_name, " +
            "COUNT(MONTH(subscription_date)) / COUNT(DISTINCT MONTH(subscription_date)) AS count_per_month " +
            "FROM purchaselist GROUP BY course_name";

    public static void main(String[] args) throws SQLException {
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("course_name") + "\t" +
                            resultSet.getString("count_per_month"));
                }
            }
        } catch (NullPointerException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }
    }
}
