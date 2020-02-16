package Transactions;

import Transactions.exceptions.WithdrawNotPossibleException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
/**
 * Класс WithdrawAccountTest.
 * JUnit тестирование операции списания со счета.
 * @author OLeg Bech.
 * @email oleg071984@gmail.com
 */
public class WithdrawAccountTest {

    Account acc = new Account("1",1000);
    Account acc1 = new Account("2",5000);
    Account acc2 = new Account("3", 10000);

    @Before
    public void blockAccount() {
        acc2.setBlocked(true);
    }

    /**
     * Тест списания со счета когда сумма 0.
     */
    @Test(expected = WithdrawNotPossibleException.class)
    public void whenAmount0ThenThrowWithdrawOperationNotPossibleException() throws WithdrawNotPossibleException {
        acc.withdraw(0);
    }

    /**
     * Тест списания со счета когда сумма меньше нуля.
     */
    @Test(expected = WithdrawNotPossibleException.class)
    public void whenAmountNegativeThenThrowWithdrawOperationNotPossibleException() throws WithdrawNotPossibleException {
        acc.withdraw(-500);
    }

    /**
     * Тест списания со счета когда сумма больше нуля и меньше баланса счета и счет не заблокирован.
     */
    @Test
    public void whenAmountMoreThan0ThenWithdrawOk() throws WithdrawNotPossibleException {
        acc.withdraw(600);
        assertEquals(400, acc.getBalance().intValue());
    }

    /**
     * Тест списания если сумма списания больше баланса счета.
     */
    @Test(expected = WithdrawNotPossibleException.class)
    public void whenAmountBiggerThenThrowWithdrawOperationNotPossibleException() throws WithdrawNotPossibleException {
        acc.withdraw(2000);
    }

    /**
     * Тест списания если счет заблокирован.
     */
    @Test(expected = WithdrawNotPossibleException.class)
    public void whenAccountBlockedThenThrowWithdrawOperationNotPossibleException() throws WithdrawNotPossibleException {
        acc2.withdraw(2000);
    }

    /**
     * Тест списания со счета в многопоточной среде.
     */
    @Test
    public void testWithdrawBySomeThreads() {
        CompletableFuture.runAsync(() -> {
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                threads.add(new Thread(() -> {
                    try {
                        acc1.withdraw(10);
                    } catch (WithdrawNotPossibleException e) {
                        e.printStackTrace();
                    }
                }));
            }
            threads.forEach(Thread::start);
            assertEquals(4000, acc1.getBalance().intValue());
        });
    }
}
