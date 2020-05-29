package Homework_14_7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Домашнее задание 14.7
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-inf.java
 */
public class Loader {

    private static final int THREAD_AMOUNT = 15;
    private static final int REGION_AMOUNT = 100;

    public static void main(String[] args) throws Exception {
        if (THREAD_AMOUNT > 0 && REGION_AMOUNT > 0) {
            long start = System.currentTimeMillis();
            int finishReqionCode = 0;
            int startRegionCode = 1;
            int z = REGION_AMOUNT - REGION_AMOUNT % THREAD_AMOUNT;
            char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            ExecutorService pool = Executors.newFixedThreadPool(THREAD_AMOUNT);
            for (int i = 0; i < THREAD_AMOUNT; i++) {
                finishReqionCode = z - ((z - finishReqionCode) - z / THREAD_AMOUNT);
                pool.submit(new FileWriterThread(startRegionCode, finishReqionCode, letters, i, start));
                System.out.println(new StringBuilder("Thread#").append(i).append(" started. Write regions from ")
                        .append(startRegionCode).append(" to ").append(finishReqionCode).toString());
                startRegionCode = finishReqionCode;
            }
            if (z != REGION_AMOUNT) {
                pool.submit(new FileWriterThread(z, REGION_AMOUNT, letters, THREAD_AMOUNT, start));
                System.out.println(new StringBuilder("Thread#").append(THREAD_AMOUNT).append(" started. Write regions from ")
                        .append(z).append(" to ").append(REGION_AMOUNT).toString());
            }
            pool.shutdown();
        } else {
            System.out.println("Incorrect values of work parameters. Work stopped!");
        }
    }

}
