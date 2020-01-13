package BankStatement;

import BankStatement.pojo.BankAccount;
import BankStatement.pojo.Operation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.util.stream.Collectors;

public class Main {

    private static final String DATA_PATCH = "Stage9/Data/movementList.csv";
    private static final DateTimeFormatter CSV_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");

    public static void main(String[] args) throws IOException, ParseException {

        List<Operation> opList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(DATA_PATCH)), 1000);
        Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(br);

        for (CSVRecord record : csvRecords) {
            opList.add(new Operation(
                    new BankAccount(record.get("Номер счета"), record.get("Тип счёта"), record.get("Валюта")),
                    LocalDate.parse(record.get("Дата операции"), CSV_DATE_FORMATTER),
                    record.get("Референс проводки"),
                    record.get("Описание операции"),
                    getDouble(record.get("Приход")),
                    getDouble(record.get("Расход"))
            )
            );

        }
        System.out.println("Общий приход: ");
        for (Currency currency : Currency.values()) {
            System.out.println(currency);
            System.out.println(
                    opList.stream().filter(p -> p.getBankAccount().getCurrency().equals(currency.toString())).
                            mapToDouble(Operation::getIncome).sum()
            );
        }
        System.out.println("Общий расход: ");
        for (Currency currency : Currency.values()) {
            System.out.println(currency);
            System.out.println(
                    opList.stream().filter(p -> p.getBankAccount().getCurrency().equals(currency.toString())).
                            mapToDouble(Operation::getFlow).sum()
            );
        }
    }

    /**
     * Метод double getDouble(String sum).
     * Конвертация строки в double.
     * @param sum строка.
     * @return значение типа double.
     * @throws ParseException
     */
    private static double getDouble(String sum) throws ParseException {
        try {
            return Double.parseDouble(sum);
        } catch (NumberFormatException nfe) {
            return Double.parseDouble(sum
                    .replaceAll("\"","")
                    .replaceAll(",","."));
        }
    }

}
