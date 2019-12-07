package homework_46.BirthDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 4.6
 */
public class Main {

    private static Calendar myBirthDate = Calendar.getInstance();

    public static void main(String[] args) {
        myBirthDate.set(1984, Calendar.JULY, 11);
        Date today = Calendar.getInstance().getTime();
        for (int i = 0; myBirthDate.getTime().before(today); i++) {
            System.out.println(i + " - " + new SimpleDateFormat("d.MM.y '-' E").format(myBirthDate.getTime()));
            myBirthDate.set(Calendar.YEAR, myBirthDate.get(Calendar.YEAR) + 1);
        }
    }
}
