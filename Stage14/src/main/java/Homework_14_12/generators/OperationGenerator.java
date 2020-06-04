package Homework_14_12.generators;

import Homework_14_12.pojo.Operation;
import Homework_14_12.utils.RandomCreditCardNumberGenerator;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Класс OperationGenerator.
 * Синглтон. Создание банковской операции с заданными и вычислемыми параметрами.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com.
 */
public class OperationGenerator {

    private static volatile OperationGenerator instance;
    private final Random random = new Random();
    private final Map<Integer, String> operationTypes = new HashMap<>();
    private final Map<Integer, String> cards = new HashMap<>();
    private double balance;
    private int maxCardsAmount;
    private String defaultCardNumber;

    private OperationGenerator(double balance, int maxCardsAmount, String defaultCardNumber) {
        this.balance = balance;
        this.maxCardsAmount = maxCardsAmount;
        this.defaultCardNumber = defaultCardNumber;
        initOperationsTypeData();
        initCardsTypeData();
    }

    /**
     * Метод getInstance.
     * Содание сингтона.
     *
     * @param balance        стартовый баланс счета.
     * @param maxCardsAmount максимальное количество карт в обороте.
     * @return сингтон класса OperationGenerator.
     */
    public static OperationGenerator getInstance(double balance, int maxCardsAmount, String defaultCardNumber) {
        OperationGenerator localInstance = instance;
        if (localInstance == null) {
            synchronized (OperationGenerator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new OperationGenerator(balance, maxCardsAmount, defaultCardNumber);
                }
            }
        }
        return localInstance;
    }

    /**
     * Метод initOperationsTypeData.
     * Создание списка возможных операций.
     */
    private void initOperationsTypeData() {
        operationTypes.put(1, "receipt");
        operationTypes.put(2, "debiting");
        operationTypes.put(3, "transfer");
    }

    /**
     * Метод initCardsTypeData.
     * Создание коллекции банковских карт для работы со счетом.
     */
    private void initCardsTypeData() {
        for (int i = 1; i < maxCardsAmount; i++) {
            cards.put(i, RandomCreditCardNumberGenerator.generateMasterCardNumber());
        }
    }

    /**
     * Метод getRandomOperation.
     * Создание банковской операции c учетом текущего баланса счета.
     *
     * @param operationSum сумма операции.
     * @return объект класса Operation.
     */
    public Operation getRandomOperation(double operationSum) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setCurrency(Currency.getInstance(Locale.getDefault()));
        switch (operationTypes.getOrDefault(random.nextInt(maxCardsAmount), "receipt")) {
            case "receipt":
                balance += operationSum;
                return new Operation(getRandomCard(),
                        "receipt", operationSum, decimalFormat.getCurrency().toString(), balance, "ok");
            case "debiting":
                if (balance < operationSum) {
                    return new Operation(getRandomCard(),
                            "debiting", operationSum, decimalFormat.getCurrency().toString(), balance, "cancelled");
                }
                balance -= operationSum;
                return new Operation(getRandomCard(),
                        "debiting", operationSum, decimalFormat.getCurrency().toString(), balance, "ok");
            case "transfer":
                if (balance < operationSum) {
                    return new Operation(getRandomCard(),
                            "transfer", operationSum, decimalFormat.getCurrency().toString(), balance, "cancelled");
                }
                balance -= operationSum;
                return new Operation(getRandomCard(),
                        "debiting", operationSum, decimalFormat.getCurrency().toString(), balance, "ok");
        }
        return null;
    }

    /**
     * Метод getRandomCard.
     * Берет из мапы произвольный номер карты.
     *
     * @return номер карты.
     */
    public String getRandomCard() {
        return cards.getOrDefault(random.nextInt(maxCardsAmount), "5170654930138539");
    }

}
