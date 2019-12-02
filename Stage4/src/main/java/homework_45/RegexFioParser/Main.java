package homework_45.FioParser;

import java.util.Scanner;

/**
 * 4.5.3
 */
public class Main {
    private static final String[] FIO_DETAILS = {"Фамилия", "Имя", "Отчество"};

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите фамилию имя и отчество человека в формате <Фамилия Имя Отчество>:");
            String userInput = (new Scanner(System.in).nextLine());
            if (!checkString(userInput)) {
                System.out.println("!> строка не соответствует предложенному формату");
                continue;
            }
            String[] fioArr = userInput.split(" ");
            for (int i = 0; i < fioArr.length; i++)
                System.out.println(FIO_DETAILS[i] + ": " + fioArr[i]);
        }
    }

    private static boolean checkString(String fio) {
        /* 4.4.4* Проверка формата ввода */
        String mask = "^[А-Я&&[^ЬЪ]][а-я&&[^ъ]]+(ов|ова|ёв|ёва|ев|ева|ин|ина|ын|ына|их|ых|ский|ская|цкий|цкая|ь) " +
                "[А-Я&&[^ЬЪ]][а-я&&[^ъ]]+ [А-Я&&[^ЬЪ]][а-я&&[^ъ]]+(ович|евич|ич|овна|евна|ична|инична)$";
        return fio.matches(mask);
    }
}
