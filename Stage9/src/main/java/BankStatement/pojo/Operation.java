package BankStatement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * Класс Operation.
 * Реализация банковской операции.
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@Data
@AllArgsConstructor
public class Operation {

    private BankAccount bankAccount; //банковский счет
    private LocalDate operationDate; //дата операции
    private String bankingReference; //референс проводки
    private String description; //описание оперции
    private String shortDescription; //краткое описание
    private double income; //приход
    private double flow; //расход

}
