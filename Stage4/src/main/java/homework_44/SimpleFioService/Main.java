package homework_44.SimpleFioService;

import java.util.Scanner;

/**
 * 4.4.3 и задание со звездочкой
 */
public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите ФИО в формате <Фамилия Имя Отчество>:");
            String fullName = new Scanner(System.in).nextLine().trim();

            if (!checkFullName(fullName)) {
                System.out.println("!> Неверный формат ФИО");
                continue;
            }

            String familyString = fullName.substring(0, fullName.indexOf(" ")).trim();
            String nameString = fullName.substring(fullName.indexOf(" "), fullName.lastIndexOf(" ")).trim();
            String patronymicString = fullName.substring(fullName.lastIndexOf(" "), fullName.length()).trim();

            if (checkUpperCase(familyString))
                System.out.println("Фамилия: " + familyString);
            if (checkUpperCase(nameString))
                System.out.println("Имя: " + nameString);
            if (checkUpperCase(patronymicString))
                System.out.println("Отчество: " + patronymicString);
        }
    }

    /**
     * Проверяем, начинается ли слово с заглавной буквы.
     * @param str слово
     * @return true - если слово начинается с заглавной
     */
    private static boolean checkUpperCase(String str) {
        return !str.isEmpty() && Character.isUpperCase(str.toCharArray()[0]);
    }

    /**
     * Проверяем ввод пользователя.
     * @param str строка, введенная пользователем.
     * @return true если введенная строка содержит 2 пробела и расстояние между ними минимум 2 символа.
     */
    private static boolean checkFullName(String str) {
        return (str.trim().lastIndexOf(" ") - str.trim().indexOf(" ") > 2);
    }
}
