package homework_78;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс Flights.
 * Выполнение задачи 7.8.2.
 * @author Oleg Bech.
 * @email oleg071984@gmail.com.
 */
public class Flights {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        Airport airport = Airport.getInstance();
        List<Flight> flightsList = new ArrayList<>();
        List<Terminal> terminals = airport.getTerminals();
        Date now = new Date();

        terminals.forEach(t -> flightsList.addAll(t.getFlights()));

        flightsList.stream().filter(f -> sdf.format(f.getDate()).equals(sdf.format(now)))
                .filter(f -> getHour(f.getDate()) > LocalDateTime.now().getHour() && getHour(f.getDate())
                        < LocalDateTime.now().getHour() + 2)
                .forEach(f -> System.out.println(f.getDate() + " > " + f.getAircraft()));


    }

    /**
     * Метод int getHour(Date date).
     * Возвращает значение часа из указанной даты. Замена для deprecated метода java.util.Date.getHours().
     * @param date - дата, из которой необходимо извлечь значение часа.
     * @return значение часа.
     */
    private static int getHour(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }
}
