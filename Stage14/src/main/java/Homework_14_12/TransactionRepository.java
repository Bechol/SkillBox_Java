package Homework_14_12;

import Homework_14_12.pojo.TransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Класс TransactionRepository.
 * Слой доступа к данным.
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionDTO, String> {
}
