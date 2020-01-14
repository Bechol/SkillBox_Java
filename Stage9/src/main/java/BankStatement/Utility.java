package BankStatement;

import lombok.extern.log4j.Log4j2;

/**
 * Утилитный класс Utility.
 * Служит для подержки преобразования банковской выписки.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Log4j2
public class Utility {

    private Utility() {
    }

    /**
     * Метод double getDouble(String sum).
     * Конвертация строки в double.
     *
     * @param sum строка.
     * @return значение типа double.
     */
    public static double getDouble(String sum) {
        try {
            return Double.parseDouble(sum);
        } catch (NumberFormatException nfe) {
            log.warn(sum + " --> Исправлен некорректный тип данных.");
            return Double.parseDouble(sum
                    .replaceAll("\"", "")
                    .replaceAll(",", "."));
        } catch (NullPointerException npe) {
            log.error("!> Не указана сумма для преобразования.");
            return 0.0;
        }
    }

    /**
     * Метод String getShortDescription(String fullDescription).
     * Возвращает краткое описание для статистики.
     *
     * @param fullDescription полное описание операции из файла данных.
     * @return краткое описание.
     */
    public static String getShortDescription(String fullDescription) {
        try {
            String[] tmpArr = fullDescription.split("\\s{3,}");
            return tmpArr[1].lastIndexOf("\\") >= 0 ?
                    tmpArr[1].substring(tmpArr[1].lastIndexOf("\\") + 1).trim().toUpperCase() :
                    tmpArr[1].substring(tmpArr[1].lastIndexOf("/") + 1).trim().toUpperCase();
        } catch (NullPointerException npe) {
            log.error("!> Полное описание не найдено.");
            return "нет краткого описания";
        }
    }
}