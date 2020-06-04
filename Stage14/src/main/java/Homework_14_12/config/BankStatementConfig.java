package Homework_14_12.config;

import Homework_14_12.TransactionService;
import Homework_14_12.generators.OperationGenerator;
import Homework_14_12.generators.StatementGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Random;

/**
 * Класс BankStatementConfig.
 * Конфиг Spring Boot.
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@Configuration
public class BankStatementConfig {

    @Autowired
    private TransactionService transactionService;

    /**
     * Количество дней, начиная с я с сегодняшнего, за которые делаем выписку
     */
    private static final int DAYS_AMOUNT = 10;
    /**
     * Максимальное колчиество транзакций в день
     */
    private static final int MAX_OPERATIONS_AMOUNT_FOR_DAY = 1000;
    /**
     * Максимальное количество банковских карт участвующих в обороте за один день
     */
    private static final int MAX_CARDS_AMOUNT = 4;
    /**
     * Стартовый баланс операционного счета
     */
    private static final long START_BALANCE = 50_000;
    /**
     * Путь к файлу для записи результатов
     */
    private static final String RESULT_FILE_PATCH = "res/bankStatement.json";
    /**
     * Дефолтный номер банковской карты.
     */
    private final static String DEFAULT_CARD_NUMBER = "5170654930138539";

    @Bean
    public StatementGenerator createBankStatementGenerator() {
        return new StatementGenerator(
                DAYS_AMOUNT, MAX_OPERATIONS_AMOUNT_FOR_DAY, RESULT_FILE_PATCH);
    }

    @Bean
    public OperationGenerator createBankDataGeneratorInstance() {
        return OperationGenerator.getInstance(START_BALANCE, MAX_CARDS_AMOUNT, DEFAULT_CARD_NUMBER);
    }

    @Bean
    public Random randomGenerator() {
        return new Random();
    }


    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return objectMapper;
    }

    @Bean
    public SmartInitializingSingleton importProcessor() {
        return () -> {
            try {
                createBankStatementGenerator().generateBankStatementJSON();
                transactionService.sendToDB();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

    }
}
