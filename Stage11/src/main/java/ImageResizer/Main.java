package ImageResizer;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Домашняя работа 11.4.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-info.java
 */
public class Main {
    /**
     * Путь к папке с изображениями.
     */
    private final static String SOURCE_FOLDER_PATH = "d:/sourse_pic";
    /**
     * Путь к папке с миниатюрами изображений.
     */
    private final static String RESULT_FOLDER_PATH = "d:/result_pic";
    /**
     * Количество ядер процессора.
     */
    private final static int CORES_AMOUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        try {
            final File sourceFolder = createFolder(SOURCE_FOLDER_PATH);
            final File destinationFolder = createFolder(RESULT_FOLDER_PATH);

            final AtomicInteger counter = new AtomicInteger(0);

            assert sourceFolder != null;
            List<File> picList = Arrays.asList(Objects.requireNonNull(sourceFolder.listFiles()));

            picList.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / divider(picList))).values()
                    .forEach(picsList -> new ThumbnailCreator(picsList, destinationFolder).run());
        } catch (NullPointerException npe) {
            System.out.println("Некорректные параметры запуска.");
        }

    }

    /**
     * Метод int divider(List<File> files).
     * Вычисляет делитель для разбиения заданной коллекции файлов на равные части.
     *
     * @param files коллекция файлов, которую необъходимо разделить.
     * @return делитель.
     */
    private static int divider(List<File> files) {
        return files != null && files.size() > 0 ? (files.size() - files.size() % CORES_AMOUNT) / CORES_AMOUNT : -1;
    }

    /**
     * Метод File createFolder(String path).
     * Проверяет существует ли по указанному path папка и является ли она папкой.
     *
     * @param path путь к папке с файлами.
     * @return экземпляр класса File.
     */
    private static File createFolder(String path) {
        File result = new File(path);
        if (result.exists() && result.isDirectory()) {
            return result;
        }
        return null;
    }
}
