package homework_52;

import java.util.*;

/**
 * 5.2.1
 * Разработать список дел, которым можно управлять командами в консоли.
 * Команды LIST, ADD, EDIT, DELETE. LIST должен выводить дела с их порядковыми номерами.
 * ADD - добавлять дело в конец списка или дело на определённое место, сдвигая остальные дела вперёд,
 * если указать номер. EDIT - заменять дело с указанным номером. DELETE - удалять. Примеры команд:
 * LIST
 * ADD Какое-то дело
 * ADD 4 Какое-то дело на четвёртом месте
 * EDIT 3 Новое название дела
 * DELETE 7
 */
public class ToDoList {

    /*список дел*/
    private static List<String> toDoList = new ArrayList<String>();
    /*строка, вводимая пользователем*/
    private static String userInput;

    public static void main(String[] args) {
        initToDoList();
        while (true) {
            System.out.println("Введите команду:");
            userInput = new Scanner(System.in).nextLine();
            if (!checkUserInput(userInput)) {
                System.out.println("!> введена неправильная команда");
                continue;
            }
            if (userInput.matches("HELP")) {
                printHelp();
            } else if (userInput.matches("LIST")) {
                printList();
            } else if (userInput.matches("^ADD\\s[^\\d\\s].+$")) {
                toDoList.add(userInput.substring(4).trim());
                printList();
            } else if (userInput.matches("^ADD\\s\\d+\\s.+$")) {
                try {
                    toDoList.add(getListIndex(),
                            userInput.substring(userInput.indexOf(String.valueOf(getListIndex())) + 1).trim());
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }
                printList();
            } else if (userInput.matches("^EDIT\\s\\d+\\s.+$")) {
                try {
                    toDoList.set(getListIndex(),
                            userInput.substring(userInput.indexOf(String.valueOf(getListIndex())) + 1).trim());
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }
                printList();
            } else if (userInput.matches("DELETE\\s\\d+")) {
                try {
                    toDoList.remove(getListIndex());
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }
                printList();
            } else if (userInput.matches("EXIT")) {
                System.out.println("Завершение работы.");
                System.exit(0);
            } else {
                System.out.println("!> Ошибка в синтаксисе комманды.");
            }
        }
    }

    /**
     * Заполняем список дел данными по умолчанию.
     */
    private static void initToDoList() {
        toDoList.add("Первое дело");
        toDoList.add("Второе дело");
        toDoList.add("Третье дело");
    }

    /**
     * Метод getListIndex().
     * Получение индекса элемента списка из команды.
     * Проверка индекса.
     *
     * @return индекс массива для вставки или изменения информации при условии что индекс неотрицательный и меньше
     * размера списка дел.
     */
    private static int getListIndex() {
        int result = 0;
        result = Integer.parseInt(userInput.split("\\s")[1].trim());
        if (result > 0 && result < toDoList.size()) {
            return result;
        } else {
            throw new IndexOutOfBoundsException("Дела с таким номером не существует.");
        }
    }

    /**
     * Метод printList().
     * Вывод списка дел в консоль.
     */
    private static void printList() {
        for (int i = 0; i < toDoList.size(); i++)
            System.out.println(i + ". " + toDoList.get(i));
    }

    /**
     * Метод printHelp().
     * Вывод справочной информации о командах.
     */
    private static void printHelp() {
        System.out.println("Список команд:");
        System.out.println("LIST - вывод списка дел.");
        System.out.println("ADD <описание> - добавить новое дело в конец списка.");
        System.out.println("ADD <номер дела> <описание> - добавить новое дело по номеру.");
        System.out.println("EDIT <номер дела> <новое описание> - редактировать дело по номеру.");
        System.out.println("DELETE <номер дела> - удалить дело по номеру.");
    }

    /**
     * Метод checkUserInput(String input).
     * Проверка строки введенной пользователем.
     *
     * @param input строка, введенная пользователем.
     * @return true - если пользователь ввел корректную комманду.
     */
    public static boolean checkUserInput(String input) {
        String mask = "^(LIST|ADD\\s.+|ADD\\s\\d\\s.+|EDIT\\s\\d.+|DELETE\\s\\d+|HELP|EXIT)$";
        return input.matches(mask);
    }
}
