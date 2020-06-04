package Homework_14_12.pojo;

import Homework_14_12.utils.CustomLocalDateDeserializer;
import Homework_14_12.utils.TransactionDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Класс Transaction.
 * Описание банковской транзакции.
 * @author Oleg Bech.
 * @email oleg071984@gmail.com.
 */
@Data
@NoArgsConstructor
@JsonDeserialize(using = TransactionDeserializer.class)
public class Transaction {
    private String uuid;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate date;
    @JsonUnwrapped
    private Operation operation;

    public Transaction(LocalDate date, Operation operation) {
        this.uuid = UUID.randomUUID().toString();
        this.date = date;
        this.operation = operation;
    }
}
