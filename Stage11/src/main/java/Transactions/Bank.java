package Transactions;

import Transactions.exceptions.AccountNotExistsException;
import Transactions.exceptions.BlockedAccountException;
import Transactions.exceptions.IdenticalAccountsException;
import lombok.extern.log4j.Log4j2;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

/**
 * Класс Bank.
 * Домашняя работа 11.13. Реализация банка и операций по счетам.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-info
 * @see Demonstration
 */
@Log4j2
public class Bank {

    private final Random random = new Random();
    private HashSet<Account> accounts;

    public Bank(HashSet<Account> accountSet) {
        accounts = accountSet;
    }

    /**
     * Метод isFraud.
     * Проверка операций сумма которых больше 50000.
     *
     * @param fromAccountNum номер счета отправителя.
     * @param toAccountNum   номер счета получателя.
     * @return random true/false
     * @throws InterruptedException
     */
    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum)
            throws InterruptedException {
        log.info("операция {} отправлена на проверку.", UUID.randomUUID().toString());
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * Метод getAccount().
     * Находит и возвращает счет по его номеру.
     *
     * @param accountId номер счета.
     * @return экземпляр счета.
     */
    public Account getAccount(String accountId) {
        try {
            if (isAccountExists(accountId))
                return accounts.stream()
                        .filter(account -> account.getAccountID().equals(accountId)).findAny().orElseThrow();
        } catch (AccountNotExistsException ane) {
            System.out.println(ane.getMessage());
        }
        return null;
    }

    /**
     * Метод transfer().
     * Переводит деньги со счета на счет.
     *
     * @param senderAccount    номер счета отправителя.
     * @param recipientAccount номер счета получателя.
     * @param amount           сумма для перевода.
     */
    public synchronized void transfer(String senderAccount, String recipientAccount, long amount) {
        try {
            if (getAccount(recipientAccount).isBlocked()) {
                throw new BlockedAccountException(recipientAccount);
            }
            if (senderAccount.equals(recipientAccount)) {
                throw new IdenticalAccountsException();
            }
            getAccount(senderAccount).withdraw(amount);
            getAccount(recipientAccount).replenish(amount);
            if (amount > 50000) {
                if (isFraud(senderAccount, recipientAccount)) {
                    getAccount(senderAccount).setBlocked(true);
                    getAccount(recipientAccount).setBlocked(true);
                    log.warn("Операция не прошла проверку.\nСчета заблокированы.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод getBalance().
     * Возврат остатка на счету.
     *
     * @param accountId номер счета.
     * @return баланс счета.
     */
    public long getAccountBalance(String accountId) {
        try {
            return getAccount(accountId).getBalance().get();
        } catch (NullPointerException npe) {
            return 0;
        }
    }

    /**
     * Метод isAccountExists().
     * Проверяет существование счета.
     *
     * @param accountId номер счета.
     * @return true если счет существует.
     * @throws AccountNotExistsException
     */
    private boolean isAccountExists(String accountId) throws AccountNotExistsException {
        if (accounts.stream().anyMatch(account -> account.getAccountID().equals(accountId))) {
            return true;
        } else {
            throw new AccountNotExistsException(accountId);
        }
    }
}
