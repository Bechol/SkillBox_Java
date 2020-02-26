package ToDoList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Домашняя работа 12.4
 * Создайте проект ToDoList, который при входе через браузер по основному адресу будет отвечать текущей датой
 * или случайным числом.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
