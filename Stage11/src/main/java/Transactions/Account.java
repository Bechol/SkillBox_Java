package Transactions;

import Transactions.exceptions.ReplenishNotPossibleException;
import Transactions.exceptions.WithdrawNotPossibleException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Класс Account.
 * Реализация счета в банке.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Data
@AllArgsConstructor
@Log4j2
public class Account {

    /**
     * Номер счета.
      */
    private String accountID;
    /**
     * Количество денег на счете.
     */
    private AtomicLong balance;
    /**
     * Блокировка счета.
     */
    private boolean blocked;

    public Account(@NonNull String accountID, long balance) {
        this.accountID = accountID;
        this.balance = new AtomicLong(balance);
    }

    /**
     * Метод replenish.
     * Пополнение счета на заданную сумму.
     * @param amount сумма пополнения.
     * @return true если счет пополнен.
     */
    public boolean replenish(long amount) throws ReplenishNotPossibleException {
        if (isLegalOperation(amount)) {
            balance.addAndGet(amount);
            log.info("пополнение счета {} на сумму {}",accountID, amount);
            return true;
        } else {
            throw new ReplenishNotPossibleException(accountID);
        }
    }

    /**
     * Метод withdraw.
     * Списание со счета на заданную сумму.
     * @param amount сумма списания.
     */
    public boolean withdraw(long amount) throws WithdrawNotPossibleException {
        if (isLegalOperation(amount) && balance.get() > amount) {
            balance.accumulateAndGet(amount, (x, y) -> x - y);
            log.info("списание со счета {} на сумму {}", accountID, amount);
            return true;
        } else {
            throw new WithdrawNotPossibleException(accountID);
        }
    }

    /**
     * Метод isLegalOperation.
     * Проверка основных параметров операции перед выполнением.
     * @param amount сумма операции.
     * @return true если сумма операции больше 0 и счет не блокирован.
     */
    private boolean isLegalOperation(long amount) {
        return !blocked && amount > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getAccountID().equals(account.getAccountID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountID());
    }
}
