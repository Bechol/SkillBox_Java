package Transactions.exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Класс WithdrawNotPossibleException.
 * Реализация исключения если списание со счета не возможно.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@AllArgsConstructor
@Log4j2
public class WithdrawNotPossibleException extends Exception {

    private String accountId;

    @Override
    public void printStackTrace() {
        log.error("Списание по счету{} невозможно.", accountId);
    }

    @Override
    public String getMessage() {
        return "Списание по счету №" + accountId + " невозможно.";
    }
}
