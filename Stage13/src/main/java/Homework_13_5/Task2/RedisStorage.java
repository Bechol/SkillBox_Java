package Homework_13_5.Task2;

import org.redisson.Redisson;
import org.redisson.api.RDeque;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Класс RedisStorage.
 * Работа с котейнером Redis.
 * Домашняя работа 13.5.
 * Задание 2.
 * Создаём контейнер Redis командой docker run --rm --name skill-redis -p 6379:6379 -d redis.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-info.java
 */
public class RedisStorage {

    /**
     * Количество зарегистрированных пользователей.
     */
    public static final int USERS_AMOUNT = 20;

    private RedissonClient redissonClient;
    private RDeque<Integer> registeredUsersIds;


    /**
     * Метод init().
     * Подключение к docker-контейнеру Redis.
     */
    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redissonClient = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Failed to connect to Redis");
            System.out.println(Exc.getMessage());
        }
        registeredUsersIds = redissonClient.getDeque("users");
    }

    /**
     * Метод initData();
     * Инициализация данных.
     */
    public void initData() {
        for (int i = 0; i < USERS_AMOUNT; i++) {
            registeredUsersIds.add(i);
        }
    }

    /**
     * Метод addLast.
     * Добавление id пользователя в конец очереди показа.
     * @param userId - id пользователя.
     */
    public void addLast(int userId) {
        registeredUsersIds.addLast(userId);
    }

    /**
     * Метод pushUser.
     * Доавление Id пользователя в самое начало очереди показа.
     * @param userId  - Id пользователя.
     */
    public void pushUser(int userId) {
        registeredUsersIds.push(userId);
    }

    /**
     * Метод peekFirstUser.
     * Возврат без удаления первого в очереди Id пользователя.
     * @return - Id пользователя.
     */
    public Integer peekFirstUser() {
        return registeredUsersIds.peekFirst();
    }

    /**
     * Метод removeFirstUser.
     * Удаление и возврат первого в очереди показа Id пользователя.
     * @return  - Id пользовтеля.
     */
    public Integer removeFirstUser() {
        return registeredUsersIds.removeFirst();
    }


}
