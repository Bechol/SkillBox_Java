package Homework_13_3.Task2;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс RedisStorage.
 * Работа с котейнером Redis.
 * Домашняя работа 13.3.
 * Задание 2.
 * Создаём контейнер Redis командой docker run --rm --name skill-redis -p 6379:6379 -d redis.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-info.java
 */

public class RedisStorage {

    private static final String DOCKER_ADDRESS = "redis://127.0.0.1:6379";
    private RedissonClient redissonClient;
    private RMap<String, Map<String, Integer>> studentsMap;

    /**
     * Метод init().
     * Подключение к docker-контейнеру Redis.
     */
    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress(DOCKER_ADDRESS);
        try {
            redissonClient = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Failed to connect to Redis");
            System.out.println(Exc.getMessage());
        }

        studentsMap = redissonClient.getMap("students");
    }

    /**
     * Метод initData.
     * Загрузка данных по условиям задачи в Redis.
     */
    public void initData() {
        Map<String, Integer> courses = new HashMap<>();
        courses.put("Web dev", 1);
        courses.put("Data Science", 4);
        studentsMap.fastPut("Ivanov I.I.", courses);
    }

    /**
     * Метод hgettall().
     * Вывод всех информации по всем студентам.
     */
    public void hgettall() {
        studentsMap.readAllEntrySet().forEach(System.out::println);
    }

    /**
     * Метод increment.
     * Увелиивает количество проиденных заданий курса на заданную величину.
     * @param studentFio ФИО студента.
     * @param course наименование курса.
     * @param inc количество выполненных заданий.
     * @return true - если количество пройденных заданий курса увеличено на величину inc.
     */
    public boolean increment(String studentFio, String course, int inc) {
        //проверяем есть существует ли студент с указанным ФИО
        if (!studentsMap.containsKey(studentFio) || inc <= 0) {
            return false;
        }
        //получаем мапу курсов студента по его фио
        Map<String, Integer> studentCoursesMap = studentsMap.get(studentFio);
        //проверяем проходит ли студент указанный курс
        if (!studentCoursesMap.containsKey(course)) {
            return false;
        }
        //обновляем количество пройденных курсов на значение параметра inc
        studentCoursesMap.put(course, studentCoursesMap.get(course) + inc);
        //асинхронно обновляем данные студента в Redis
        studentsMap.fastPutAsync(studentFio, studentCoursesMap);
        return true;
    }
}
