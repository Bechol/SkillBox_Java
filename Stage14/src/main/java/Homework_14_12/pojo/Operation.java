package Homework_14_12.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс Operation.
 * Описание операции со счетом.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Operation {

    private String account;
    private String type;
    private Double sum;
    private String currency;
    private Double balance;
    private String status;

}
