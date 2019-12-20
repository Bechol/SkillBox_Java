package homework_55;

import java.util.*;

/**
 * 5.5
 * Написать генератор блатных автомобильных номеров и реализовать поиск элементов в списке прямым перебором,
 * бинарным поиском, поиском с помощью HashSet и с помощью TreeSet. Измерить и сравнить длительность
 * 4-х видов поиска и написать результат в качестве решения домашнего задания.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class NumberGenerator {

    private static List<String> numbers = new ArrayList<>();
    private final static String[] NUMBER_CHARS = new String[]{"C", "M", "T", "B", "A", "P", "O", "H", "E", "У"};

    public static void main(String[] args) {
        numbers = numbersGemerator();
        while (true) {
            System.out.println("Введите номер автомобиля:");
            String searchNumber = new Scanner(System.in).nextLine();

            bruteForceSearch(searchNumber);
            binarySearch(searchNumber);

            TreeSet<String> numbersTreeSet = new TreeSet<>();
            numbersTreeSet.addAll(numbers);
            System.out.println("=======TreeSet======");
            long start = System.currentTimeMillis();
            if (numbersTreeSet.contains(searchNumber)) {
                System.out.println("Номер найден. Время поиска: " + (System.currentTimeMillis() - start) + "мс");
            } else {
                System.out.println("Номер не найден");
            }

            HashSet<String> numbersHashSet = new HashSet<>();
            numbersHashSet.addAll(numbers);
            System.out.println("=======HashSet======");
            long start1 = System.currentTimeMillis();
            if (numbersHashSet.contains(searchNumber)) {
                System.out.println("Номер найден. Время поиска: " + (System.currentTimeMillis() - start1) + "мс");
            } else {
                System.out.println("Номер не найден");
            }
        }
    }

    /**
     * Метод numbersGenerator()
     * Создание коллекции блатных номеров.
     *
     * @return коллекция блатных номеров.
     */
    private static List<String> numbersGemerator() {
        int regionCode = 1;
        List<String> result = new ArrayList<>();
        while (regionCode < 200) {
            for (String aChar : NUMBER_CHARS) {
                for (int j = 1; j < 1000; j++) {
                    if (regionCode < 100) {
                        result.add(String.format("%s%03d%s%s%02d", aChar, j, aChar, aChar, regionCode));
                    } else {
                        result.add(String.format("%s%03d%s%s%03d", aChar, j, aChar, aChar, regionCode));
                    }
                }
            }
            regionCode++;
        }
        return result;
    }

    private static void bruteForceSearch(String myNumber) {
        System.out.println("=======Поиск перебором======");
        long start = System.currentTimeMillis();
        for (String number : numbers) {
            if (number.equals(myNumber)) {
                System.out.println("Номер найден. Время поиска: " + (System.currentTimeMillis() - start) + "мс");
                break;
            }
        }
    }


    private static void binarySearch(String myNumber) {
        System.out.println("=======Двоичный поиск======");
        Collections.sort(numbers);
        long start = System.currentTimeMillis();
        long result = Collections.binarySearch(numbers, myNumber);
        if (result != -1) {
            System.out.println("Номер найден. Время поиска: " + (System.currentTimeMillis() - start) + "мс");
        } else {
            System.out.println("Номер не найден.");
        }
    }
}
