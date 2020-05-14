package Homework_13_5.Task1;

import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Класс RedisStorage.
 * Работа с котейнером Redis.
 * Домашняя работа 13.5.
 * Задание 1.
 * Создаём контейнер Redis командой docker run --rm --name skill-redis -p 6379:6379 -d redis.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-info.java
 */
public class RedisStorage {

    private final static List<String> USER_TOWNS = Arrays.asList(
            "New York",
            "Praga",
            "London",
            "Venice",
            "Vancuver",
            "San-Francisco",
            "Rome",
            "Lissabon",
            "Budapesht",
            "Stambul",
            "Tokyo");
    private final Random flightPriceGenerator = new Random();
    private RedissonClient redissonClient;
    private RScoredSortedSet<String> towns;


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
        towns = redissonClient.getScoredSortedSet("towns");
    }

    /**
     * Метод initData.
     * Загрузка в Redis данных по условию задания.
     */
    public void initData() {
        USER_TOWNS.forEach(userTown -> towns.add(flightPriceGenerator.nextInt(50000), userTown));
    }

    /**
     * Метод readAll.
     * Вывод в консоль всех городов и цен на билет.
     */
    public void readAll() {
        System.out.println("All prices for towns:");
        towns.readAll().forEach(town -> System.out.println(town + " > " + towns.getScore(town)));
    }

    /**
     * Метод showCheapestFlights.
     * Вывод n самых дешевых билетов в порядке возрастания.
     *
     * @param amount количество билетов в выборке.
     */
    public void showCheapestFlights(int amount) {
        if (amount > 0 && amount < towns.size()) {
            System.out.println("\n" + amount + " cheapest flights:");
            towns.readAll().stream()
                    .sorted((town1, town2) -> towns.getScore(town1).compareTo(towns.getScore(town2)))
                    .limit(amount)
                    .forEach(town -> System.out.println(town + " > " + towns.getScore(town)));
        }
    }

    /**
     * Метод showMostExpensive.
     * Вывод n самых дорогих билетов в порядке убывания.
     *
     * @param amount количество билетов в выборке.
     */
    public void showMostExpensive(int amount) {
        if (amount > 0 && amount < towns.size()) {
            System.out.println("\n" + amount + " most expensive flights:");
            towns.readAll().stream()
                    .sorted((town1, town2) -> towns.getScore(town2).compareTo(towns.getScore(town1)))
                    .limit(amount)
                    .forEach(town -> System.out.println(town + " > " + towns.getScore(town)));
        }
    }


}
