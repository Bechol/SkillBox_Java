package homework_53;

import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 5.3
 * Написать программу, в которой будет храниться перечень e-mail-ов.
 * E-mail’ы  можно добавлять через консоль командой ADD и печатать весь список командой LIST.
 * * Проверять корректность вводимых e-mail’ов и в случае необходимости печатать сообщение об ошибке.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class EmailList {
    /*строка, вводимая пользователем*/
    private static String userInput;
    /*сет постовых адресов*/
    private static HashSet<String> emailSet = new HashSet<>();

    public static void main(String[] args) {
        emailSetInit();
        while (true) {
            System.out.println("Введите команду:");
            userInput = new Scanner(System.in).nextLine();
            if (!checkUserInput(userInput)) {
                System.out.println("!> введена неправильная команда");
                continue;
            }
            if (userInput.matches("LIST")) {
                emailSet.forEach(System.out::println);
            } else if (userInput.matches("^ADD\\s[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")) {
                if (!emailSet.add(userInput.split("\\s")[1]))
                    System.out.println("Указанный email есть в списке.");
            } else if (userInput.matches("EXIT")) {
                System.out.println("Завершение работы.");
                System.exit(0);
            } else {
                System.out.println("Ошибка в синтаксисе команды.");
            }
        }
    }

    /**
     * Метод emailSetInit().
     * Загрузка в emailSet значений по умолчанию.
     */
    private static void emailSetInit() {
        emailSet.add("oleg071984@gmail.com");
        emailSet.add("abc@mail.ru");
        emailSet.add("ok@yandex.ru");
    }

    /**
     * Метод checkUserInput(String input).
     * Проверка строки введенной пользователем.
     *
     * @param input строка, введенная пользователем.
     * @return true - если пользователь ввел корректную комманду.
     */
    private static boolean checkUserInput(String input) {
        String email_regex = "^(EXIT|LIST|ADD\\s[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,}))$";
        return input.matches(email_regex);
    }
}
