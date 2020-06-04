package Homework_14_12.generators;

import Homework_14_12.pojo.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс StatementGenerator.
 * Генерация и запись в файл банковской выписки.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com.
 */
@Slf4j
public class StatementGenerator {

    private static final LocalDate TODAY = LocalDate.now();
    private final int daysAmount;
    private final int maxOperationsAmountForDay;
    private final String resultFilePatch;
    @Autowired
    private OperationGenerator operationGenerator;
    @Autowired
    private Random random;
    @Autowired
    private ObjectMapper mapper;

    public StatementGenerator(int daysAmount, int maxOperationsAmountForDay, String resultFilePatch) {
        this.daysAmount = daysAmount;
        this.maxOperationsAmountForDay = maxOperationsAmountForDay;
        this.resultFilePatch = resultFilePatch;
    }

    /**
     * Метод getFileSizeMegaBytes.
     * Расчет веса json файла в мегабайтах.
     *
     * @param file сгенерированный hson файл.
     * @return размер в мегабайтах.
     */
    private static String getFileSizeMegaBytes(File file) {
        return (double) file.length() / (1024 * 1024) + "mb";
    }

    /**
     * Метод generateBankStatementJSON.
     * Генерация, запись в файл банковской выписки и вывод отчета по созданному файлу.
     *
     * @throws IOException
     */
    public void generateBankStatementJSON() throws IOException {
        File resultJsonFile = new File(resultFilePatch);

        List<Transaction> transactionList = new ArrayList<>();

        for (int i = 1; i <= daysAmount; i++) {
            for (int j = 0; j < random.nextInt(maxOperationsAmountForDay); j++) {
                transactionList.add(new Transaction(TODAY.minusDays(i),
                        operationGenerator.getRandomOperation(Math.random() * 100000)
                ));
            }
        }
        mapper.writeValue(resultJsonFile, transactionList);
        log.info("Created : {}. Size: {}. Transactions: {}.",
                resultJsonFile.getAbsolutePath(), getFileSizeMegaBytes(resultJsonFile), transactionList.size());
    }

}