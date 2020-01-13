package BankStatement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс BankAccount.
 * Реализация банковского счета.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Data
@AllArgsConstructor
public class BankAccount {
    private String id; //номер
    private String type; //тип
    private String currency; //валюта
}
