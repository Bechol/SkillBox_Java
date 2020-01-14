package FolderCopy;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;



/**
 * Домашняя работа 9.7
 * Написать код, который будет копировать указанную папку с файлами с сохранением структуры в другую указанную папку.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class Main {

    private static final Path SRC_DIR = Paths.get("Stage9/src/main/java/FolderCopy/primer/src_dir");
    private static final Path DEST_DIR = Paths.get("Stage9/src/main/java/FolderCopy/primer/dst_dir");

    public static void main(String[] args) throws IOException {
        try{
            long start = System.nanoTime();
            Files.walkFileTree(SRC_DIR, new CopyFiles(SRC_DIR, DEST_DIR));
            long end = System.nanoTime();
            System.out.println((end - start) / 1000000 + " мс");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
