package Transactions.exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Класс ReplenishNotPossibleException.
 * Реализация исключения если пополнение счет не возможно.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@AllArgsConstructor
@Log4j2
public class ReplenishNotPossibleException extends Exception {

    private String accountId;

    @Override
    public void printStackTrace() {
        log.error("Пополнение счета №{} невозможно.", accountId);
    }

    @Override
    public String getMessage() {
        return "Пополнение счета №" + accountId + " невозможно.";
    }
}
