package FolderSize;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Домашняя работа 9.5
 *
 * @autor Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-info.java
 */
public class Main {

    private static final Logger LOG = LogManager.getRootLogger();

    public static void main(String[] args) throws IOException {
        while (true) {
            try {
                System.out.println("Введите путь к папке:");
                String path = new Scanner(System.in).nextLine();
                if (path != null && !path.isEmpty()) {
                    File folder = new File(path);
                    if (!folder.exists())
                        throw new FileNotFoundException();
                    print(getFolderSize(folder));
                }
            } catch (NullPointerException npe) {
                LOG.warn("В указанной папке нет файлов.");
            } catch (FileNotFoundException sfe) {
                LOG.error("Директория не существует. Указан ошибочный путь.");
            }
        }
    }

    /**
     * Метод long getFolderSize(File folder).
     * Считает размер папки по содержимому.
     *
     * @param dir объект File, содержащий путь к интересующей папке.
     * @return размер указанной папки.
     */
    private static long getFolderSize(File dir) throws IOException {
        Path path = Path.of(dir.toURI());
        return Files.walk(path).mapToLong(p -> p.toFile().length() ).sum();
    }

    /**
     * Метод void print(long size).
     * Вывод в удобочитаемом формате размера указанной папки в принятых единицах измерения.
     *
     * @param size размер папки в байтах.
     */
    private static void print(long size) {
        String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int unitIndex = (int) (Math.log10(size) / 3);
        double unitValue = 1 << (unitIndex * 10);
        String readableSize = new DecimalFormat("#,##0.##")
                .format(size / unitValue) + " "
                + units[unitIndex];
        System.out.println(readableSize);
    }
}
