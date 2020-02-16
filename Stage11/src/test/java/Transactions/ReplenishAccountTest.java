package Transactions;

import Transactions.exceptions.ReplenishNotPossibleException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;

/**
 * Класс ReplenishAccountTest.
 * JUnit тестирование операции пополнения счета.
 *
 * @author OLeg Bech.
 * @email oleg071984@gmail.com
 */
public class ReplenishAccountTest {

    Account acc = new Account("1", 1000);
    Account acc1 = new Account("2", 2000);
    Account acc2 = new Account("3", 3000);

    @Before
    public void blockAccount() {
        acc2.setBlocked(true);
    }

    /**
     * Тест пополнения счета когда сумма 0.
     */
    @Test(expected = ReplenishNotPossibleException.class)
    public void whenAmount0ThenReplenishThrowsWithdrawNotPossibleException() throws ReplenishNotPossibleException {
        acc.replenish(0);
    }

    /**
     * Тест пополнения счета когда сумма меньше нуля.
     */
    @Test(expected = ReplenishNotPossibleException.class)
    public void whenAmountNegativeThenReplenishThrowsWithdrawNotPossibleException() throws ReplenishNotPossibleException {
        acc.replenish(-500);
    }

    /**
     * Тест пополнения заблокированного счета.
     */
    @Test(expected = ReplenishNotPossibleException.class)
    public void whenAccountBlockedThenReplenishThrowsWithdrawNotPossibleException() throws ReplenishNotPossibleException {
        acc2.replenish(500);
    }

    /**
     * Тест пополнения счета когда сумма больше нуля.
     */
    @Test
    public void whenAmountMoreThan0ThenReplenishOk() throws ReplenishNotPossibleException {
        acc.replenish(600);
        assertEquals(1600, acc.getBalance().intValue());
    }

    /**
     * Тест пополнения счета в многопоточной среде.
     */
    @Test
    public void testReplenishBySomeThreads() {
        CompletableFuture.runAsync(() -> {
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                threads.add(new Thread(() -> {
                    try {
                        acc1.replenish(10);
                    } catch (ReplenishNotPossibleException e) {
                        e.printStackTrace();
                    }
                }));
            }
            threads.forEach(Thread::start);
            assertEquals(3000, acc1.getBalance().intValue());
        });
    }
}
