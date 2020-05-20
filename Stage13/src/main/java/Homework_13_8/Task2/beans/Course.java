package Homework_13_8.Task2.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Класс Course.
 * Реализация курса обучения.
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
}
