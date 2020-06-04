package Homework_14_12.utils;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.format.DateTimeFormatter;

/**
 * Класс CustomLocalDateDeserializer.
 * Кастомный десериализатор для даты.
 * @author Oleg Bech.
 * @email oleg071984@gmail.com.
 */
public class CustomLocalDateDeserializer extends LocalDateDeserializer {

    public CustomLocalDateDeserializer() {
        super(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
