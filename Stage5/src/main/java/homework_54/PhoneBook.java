package homework_54;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 5.4
 * Написать программу, которая будет работать как телефонная книга: если пишем новое имя,
 * просит ввести номер телефона и запоминает его, если новый номер телефона — просит ввести
 * имя и тоже запоминает. Если вводим существующее имя или номер телефона, программа должна
 * выводить всю информацию о контакте. При вводе команды LIST программа должна печатать
 * в консоль список всех абонентов в алфавитном порядке с номерами.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class PhoneBook {
    /*Дерево контактов*/
    private static TreeMap<String, String> contacts = new TreeMap<>();
    /*Пользовательский ввод*/
    private static String userInput;

    private static final String NAME_REGEX = "^[А-Я][а-я]*$";
    private static final String PHONE_NUMBER_REGEX = "^8[(]\\d{3}[)]\\d{3}[-]\\d{2}[-]\\d{2}";
    private static final String TEST_REGEX = "[7|8][(|-]?\\d{3}[-|)]?\\d{3}[-]?\\d{2}[-]?\\d{2}";

    public static void main(String[] args) {
        initContacts();
        while (true) {
            System.out.println("введите фио или номер телефона:");
            userInput = new Scanner(System.in).nextLine();
            if (userInput.matches(PHONE_NUMBER_REGEX)) {
                phoneOperation();
            } else if (userInput.matches(NAME_REGEX)) {
                nameOperation();
            } else if (userInput.matches("LIST")) {
                contacts.forEach((name, phone) -> System.out.println(name + " " + phone));
            } else if (userInput.matches(TEST_REGEX)) {
                System.out.println("Test regex ok!");
                //???

            } else {
                System.out.println("!> Некорректный ввод.");
            }
        }

    }

    /**
     * Метод nameOperation().
     * Обработка нового контакта по имени.
     */
    private static void nameOperation() {
        if (contacts.containsKey(userInput)) {
            System.out.println("Найден контакт: " + contacts.ceilingEntry(userInput));
        } else {
            System.out.println("Введите номер телефона для нового контакта: ");
            for (int i = 0; i < 3; i++) {
                System.out.println("Попытка " + (i + 1));
                String newTelNum = new Scanner(System.in).nextLine();
                if (newTelNum.matches(PHONE_NUMBER_REGEX)) {
                    contacts.put(userInput, newTelNum);
                    System.out.println("новый контакт " + userInput + " успешно добавлен в телефонную книгу.");
                    break;
                } else {
                    System.out.println("!> Некорректный номер телефона.");
                }
            }
        }
    }

    /**
     * Метод phoneOperation().
     * Обработка нового контакта по номеру телефона.
     */
    private static void phoneOperation() {
        if (contacts.containsValue(userInput)) {
            System.out.println("Контакт с таким телефоном есть в телефонной книге.");
        } else {
            System.out.println("Введите имя для нового контакта: ");
            for (int i = 0; i < 3; i++) {
                System.out.println("Попытка " + (i + 1));
                String newContactName = new Scanner(System.in).nextLine();
                if (newContactName.matches(NAME_REGEX)) {
                    contacts.put(newContactName, userInput);
                    System.out.println("новый контакт " + newContactName + " успешно добавлен в телефонную книгу.");
                    break;
                } else {
                    System.out.println("!> Некорректное имя.");
                }
            }
        }
    }

    /**
     * Метод initContacts();
     * Наполнение телефонной книги контактами по умолчанию.
     */
    private static void initContacts() {
        contacts.put("Олег", "8(911)524-87-99");
        contacts.put("Виктор", "8(921)474-57-85");
        contacts.put("Аня", "8(905)421-74-56");
    }
}
