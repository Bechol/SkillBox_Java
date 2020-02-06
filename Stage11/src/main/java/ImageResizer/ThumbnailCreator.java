package ImageResizer;

import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Класс ThumbnailCreator.
 * Создает отдельный поток для уменьшения размеров картинок.
 * @autor Oleg Bech
 * @email oleg071984@gmail.com
 */
@AllArgsConstructor
public class ThumbnailCreator implements Runnable {

    private List<File> files;
    private File destinationFolder;

    @Override
    public void run() {
        try {
            for (File file : files) {
                System.out.println(file.getAbsolutePath());
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                Thumbnails.of(image).size(100, 100)
                        .toFile(destinationFolder.getAbsolutePath() + "/" + file.getName() + ".jpg");
            }
        } catch (IOException | NullPointerException ioe) {
            ioe.printStackTrace();
        }
    }
}
