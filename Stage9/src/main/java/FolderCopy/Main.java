package FolderCopy;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;



/**
 * Домашняя работа 9.7
 * Написать код, который будет копировать указанную папку с файлами с сохранением структуры в другую указанную папку.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class Main {

    private static final File SRC_DIR = new File("Stage9/src/main/java/FolderCopy/primer/src_dir");
    private static final File DEST_DIR = new File("Stage9/src/main/java/FolderCopy/primer/dst_dir");

    public static void main(String[] args) throws IOException {
        try{
            long start = System.nanoTime();
            FileUtils.copyDirectory(SRC_DIR, DEST_DIR);
            long end = System.nanoTime();
            System.out.println((end-start)/1000000 + " мс");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
