package Transactions;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Класс BankTest.
 * JUnit тестирование работы банковских операций.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class BankTest {

    private Bank bank;

    @Before
    public void init() {
        HashSet<Account> accounts = new HashSet<>();
        accounts.add(new Account("1", 15000));
        accounts.add(new Account("2", 130000));
        accounts.add(new Account("3", 2525000));
        Account blockedAccount = new Account("4", 15200);
        blockedAccount.setBlocked(true);
        accounts.add(blockedAccount);
        bank = new Bank(accounts);
    }

    /**
     * Тест попытки возврата несуществующего счета.
     */
    @Test
    public void whenAccountIsNotExistThenReturnNull() {
        assertNull(bank.getAccount("12"));
    }

    /**
     * Тест запроса баланса аккаунта.
     */
    @Test
    public void getAccountBalanceTest() {
        assertEquals(15000, bank.getAccountBalance("1"));
    }

    /**
     * Тест запроса баланса несуществующего счета.
     */
    @Test
    public void notExistsAccountGetBalanceTest() {
        assertEquals(0, bank.getAccountBalance("24"));
    }

    /**
     * Тест запроса баланса заблокированного счета.
     */
    @Test
    public void blockedAccountGetBalanceTest() {
        assertEquals(15200, bank.getAccountBalance("4"));
    }

    /**
     * Тест перевода когда сумма перевода больше нуля и счета не блокированы.
     */
    @Test
    public void legalTransferTest() {
        bank.transfer("1", "2", 7500);
        assertEquals(7500, bank.getAccount("1").getBalance().get());
        assertEquals(137500, bank.getAccount("2").getBalance().get());
    }

    /**
     * Тест перевода с блокированного счета.
     */
    @Test
    public void transferFromBlockedAccountTest() {
        bank.transfer("4", "1", 7500);
        assertEquals(15200, bank.getAccount("4").getBalance().get());
        assertEquals(15000, bank.getAccount("1").getBalance().get());
    }

    /**
     * Тест перевода на заблокированный счет.
     */
    @Test
    public void transferToBlockedAccountTest() {
        bank.transfer("1", "4", 7500);
        assertEquals(15200, bank.getAccount("4").getBalance().get());
        assertEquals(15000, bank.getAccount("1").getBalance().get());
    }

    /**
     * Тест перевода если сумма перевода равна 0.
     */
    @Test
    public void transferZeroAmountTest() {
        bank.transfer("1","2",0);
        assertEquals(15000, bank.getAccount("1").getBalance().get());
        assertEquals(130000, bank.getAccount("2").getBalance().get());
    }

    /**
     * Тест перевода если сумма перевода меньше нуля.
     */
    @Test
    public void transferNegativeAmountTest() {
        bank.transfer("1","2",-500);
        assertEquals(15000, bank.getAccount("1").getBalance().get());
        assertEquals(130000, bank.getAccount("2").getBalance().get());
    }
}
