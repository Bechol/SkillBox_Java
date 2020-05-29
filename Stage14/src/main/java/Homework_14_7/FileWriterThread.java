package Homework_14_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Класс FileWriterThread.
 * Создание потока генерации автомбильных номеров с последующей записью в файл .
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 * @see package-inf.java
 */
public class FileWriterThread extends Thread {
    /**
     * Шаблон пути сохранения файла.
     */
    private static final String FILE_PATH_TEMPLATE = "res/";
    /**
     * Расширение файла.
     */
    private static final String FILE_EXT = ".txt";
    /**
     * Для формирования строк.
     */
    private StringBuilder sb = new StringBuilder();
    /**
     * Код начального региона.
     */
    private int startRegionCode;
    /**
     * Код конечного региона.
     */
    private int finishRegionCode;
    /**
     * Массив букв.
     */
    private char[] numLetters;
    /**
     * Порядковый номер потока.
     */
    private int threadNum;
    /**
     * Стартовое значение времени.
     */
    private long start;

    public FileWriterThread(int startRegionCode, int finishRegionCode, char[] numLetters, int threadNum, long start) {
        this.startRegionCode = startRegionCode;
        this.finishRegionCode = finishRegionCode;
        this.numLetters = numLetters;
        this.threadNum = threadNum;
        this.start = start;
    }

    /**
     * Метод padNumber.
     * Генерация цифровой части номера.
     *
     * @param number       текущий регион или номер.
     * @param numberLength количество символов в номере.
     * @return номер с 0 в начале или ез него..
     */
    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, '0');
        }
        return numberStr.toString();
    }

    @Override
    public void run() {
        try {
            File resultFile = new File(createFilePath());
            PrintWriter printWriter = new PrintWriter(resultFile);
            for (int regionCode = startRegionCode; regionCode < finishRegionCode; regionCode++) {
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : numLetters) {
                        for (char secondLetter : numLetters) {
                            for (char thirdLetter : numLetters) {
                                builder.append(firstLetter);
                                builder.append(padNumber(number, 3));
                                builder.append(secondLetter);
                                builder.append(thirdLetter);
                                builder.append(padNumber(regionCode, 2));
                                builder.append("\n");
                            }
                        }
                    }
                }
                printWriter.write(builder.toString());
            }
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        printResult();
    }

    /**
     * Метод printResult.
     * Добавляет к sb новые строки и выводит сообщение.
     */
    private void printResult() {
        sb.append(": ");
        sb.append(System.currentTimeMillis() - start);
        sb.append("ms.");
        System.out.println(sb.toString());
    }

    /**
     * Метод createFilePath.
     * Создание пути для сохранения файла.
     *
     * @return пусть для сохранения файла.
     */
    private String createFilePath() {
        sb.append(FILE_PATH_TEMPLATE);
        sb.append("Thread#");
        sb.append(this.threadNum);
        sb.append("_Regions_");
        sb.append(this.startRegionCode);
        sb.append("-");
        sb.append(this.finishRegionCode);
        sb.append(FILE_EXT);
        return sb.toString();
    }
}
