package Homework_14_12.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Класс TransactionDTO.
 * Объект для записи в базу данных в денормализованной форме.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_transaction")
public class TransactionDTO {

    @Id
    private String uuid;
    private String date;
    private String account;
    private String type;
    private Double sum;
    private String currency;
    private Double balance;
    private String status;
}
