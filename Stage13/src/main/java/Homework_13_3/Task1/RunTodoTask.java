package Homework_13_3.Task1;

import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Класс RunTodoTask.
 * Демонтрация выполнения задания 13.3.1.
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 * @see package-info.java
 */
public class RunTodoTask {
    //Коллекция запланированных дел
    private static RSet<String> todoSet;
    //Клиент для контейнера Redis
    private RedissonClient redissonClient;

    public static void main(String[] args) {
        //подключаемся к нашему контейнеру Redis
        RedisStorage rs = new RedisStorage();
        rs.init();
        //коллекция запланированных дел
        Set<String> todos = new HashSet<>();
        todos.add("buy milk");
        todos.add("fill the car");
        todos.add("feed the cat");
        //добавляем все запланированные дела в Redis
        System.out.println("Add new cases to the list:");
        todos.forEach(todo -> {
            rs.addNewTodo(todo);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //выполняем дела, удаляя поочередно из сета Redis
        System.out.println("Do things from the list:");
        todos.forEach(todo -> {
            rs.doTodo(todo);
            try {
                Thread.sleep(new Random().nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
