package BankStatement;

import BankStatement.pojo.BankAccount;
import BankStatement.pojo.Operation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import static BankStatement.Utility.*;

/**
 * Класс Main.
 * Домашняя работа 9.9.
 * @see package-info.java
 * @see Utility class
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class Main {

    private static final String DATA_PATCH = "Stage9/Data/movementList.csv";
    private static final DateTimeFormatter CSV_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");

    public static void main(String[] args) throws IOException {

        List<Operation> opList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(DATA_PATCH)), 1000);
        Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(br);

        for (CSVRecord record : csvRecords) {
            opList.add(new Operation(
                            new BankAccount(record.get("Номер счета"), record.get("Тип счёта"), record.get("Валюта")),
                            LocalDate.parse(record.get("Дата операции"), CSV_DATE_FORMATTER),
                            record.get("Референс проводки"),
                            record.get("Описание операции"),
                            getShortDescription(record.get("Описание операции")),
                            getDouble(record.get("Приход")),
                            getDouble(record.get("Расход"))
                    )
            );
        }

        br.close();

        TreeSet<String> oper_types = opList.stream().map(Operation::getShortDescription).
                collect(Collectors.toCollection(TreeSet::new));

        System.out.println("\nГруппировка по приходу: ");
        oper_types.forEach(operation -> System.out.println(operation + ": " + opList.stream()
                .filter(o -> o.getShortDescription().equals(operation) && o.getIncome() > 0)
                .mapToDouble(Operation::getIncome).sum()));
        System.out.println("\nГруппировка по расходу: ");
        oper_types.forEach(operation -> System.out.println(operation + ": " + opList.stream()
                .filter(o -> o.getShortDescription().equals(operation) && o.getFlow() > 0)
                .mapToDouble(Operation::getFlow).sum()));

        System.out.println("\nОбщий приход: " + opList.stream().mapToDouble(Operation::getIncome).sum());
        System.out.println("\nОбщий расход: " + opList.stream().mapToDouble(Operation::getFlow).sum());
    }
}
