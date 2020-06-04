package Homework_14_12;

import Homework_14_12.pojo.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Класс BatchWriter.
 * Поток записи в базу данных.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@Slf4j
@AllArgsConstructor
public class BatchWriter extends Thread {

    @Autowired
    private final TransactionRepository transactionRepository;

    private final List<TransactionDTO> list;
    private final long start;

    @Override
    public void run() {
        transactionRepository.saveAll(list);
        log.info("Work time: {} ms", System.currentTimeMillis() - start);
    }
}
