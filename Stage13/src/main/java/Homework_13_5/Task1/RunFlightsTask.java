package Homework_13_5.Task1;

/**
 * Класс RunFlightsTask.
 * Демонтрация выполнения задания 13.5.1.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 * @see package-info.java
 */
public class RunFlightsTask {
    public static void main(String[] args) {
        RedisStorage rs = new RedisStorage();
        rs.init();
        rs.initData();
        rs.readAll();
        rs.showCheapestFlights(3);
        rs.showMostExpensive(3);
    }
}
