package homework_46.BirthDay;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * 4.6
 */
public class Main {

    private static LocalDate startDate = LocalDate.of(1984, Month.JULY, 11); //мой день рождения
    private static DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

    public static void main(String[] args) {
        int age = 0;
        while (startDate.isBefore(LocalDate.now())) {
            System.out.println(age + " - " + startDate.format(shortDateTime) + " - "
                    + startDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault()));
            startDate = startDate.plusYears(1);
            age++;
        }
    }
}
