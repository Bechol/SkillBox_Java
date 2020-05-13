package Task2;

/**
 * Класс RunStudentTask.
 * Домашнее задание 13.3.2
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class RunStudentTask {

    public static void main(String[] args) {
        RedisStorage rs = new RedisStorage();
        //подключаемся к docker контейнеру Redis
        rs.init();
        //добавляем данные
        rs.initData();
        //выводим все данные о студентах в консоль
        rs.hgettall();
        //увеличиваем количество пройденных заданий по заданному курсу.
        if (rs.increment("Bech O.I.", "Web dev", 21)) {
            System.out.println("OK");
            rs.hgettall();
        } else {
            System.out.println("System error.");
        }
    }
}
