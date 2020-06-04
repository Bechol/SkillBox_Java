package Homework_14_12;

import Homework_14_12.generators.StatementGenerator;
import Homework_14_12.pojo.TransactionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * Класс TransactionService.
 * Сервисный слой для TransactionDTO.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@Slf4j
@Service
public class TransactionService {
    /**
     * Определение количества потоков.
     */
    private static final int PROCESSORS_AMOUNT = Runtime.getRuntime().availableProcessors();

    @Autowired
    private StatementGenerator bankStatementGenerator;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Метод sendToDB.
     * Разбивает входящий массив данных на равные части и асинхронной записывает в базу.
     * Остаток, если есть, то тоже асинхронно пишет в базу.
     *
     * @throws IOException
     */
    public void sendToDB() throws IOException {

        long start = System.currentTimeMillis();
        int startIndex = 0;
        int finishIndex = 0;
        TransactionDTO[] arr = readSourceFile();

        int z = arr.length - arr.length % PROCESSORS_AMOUNT;
        for (int i = 0; i < PROCESSORS_AMOUNT; i++) {
            finishIndex = z - ((z - finishIndex) - z / PROCESSORS_AMOUNT);
            List<TransactionDTO> list = new ArrayList<>(Arrays.asList(arr).subList(startIndex, finishIndex));
            CompletableFuture.runAsync(
                    new BatchWriter(transactionRepository, list, start),
                    Executors.newFixedThreadPool(PROCESSORS_AMOUNT));
            list = null;
            startIndex = finishIndex;

        }
        if (z != arr.length) {
            for (int j = z; j < arr.length; j++) {
                List<TransactionDTO> list = new ArrayList<>();
                list.add(arr[j]);
                CompletableFuture.runAsync(
                        new BatchWriter(transactionRepository, list, start),
                        Executors.newFixedThreadPool(PROCESSORS_AMOUNT));
                list = null;
            }
        }
    }

    /**
     * Метод readSourceFile.
     * Чтение данных из файла.
     *
     * @return массив TransactionDTO.
     * @throws IOException
     */
    public TransactionDTO[] readSourceFile() throws IOException {
        return objectMapper.readValue(new File("res/bankStatement.json"), TransactionDTO[].class);
    }
}
