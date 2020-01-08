package ConsoleCustomerList.exceptions;

/**
 * Класс EmptyListException.
 * Исключение.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class EmptyListException extends RuntimeException {

    @Override
    public String getMessage() {
        return "В базе данных отсутствуют записи.";
    }
}
