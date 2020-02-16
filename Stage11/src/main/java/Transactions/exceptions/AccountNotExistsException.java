package Transactions.exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Класс AccountNotExistsException.
 * Реализация исключения если счет не существует.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@AllArgsConstructor
@Log4j2
public class AccountNotExistsException extends Exception {

    private String accountId;

    @Override
    public void printStackTrace() {
        log.error("Счет №{} не найден.", accountId);
    }

    @Override
    public String getMessage() {
        return "Счет №" + accountId + " не найден";
    }
}
