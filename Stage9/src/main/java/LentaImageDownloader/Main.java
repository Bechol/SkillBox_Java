package LentaImageDownloader;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Домашняя работа 9.12
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-info.java
 */
@Log4j2
public class Main {

    /**
     * Адрес сайта для парсинга картинок.
     */
    private static final String WEB_URL = "https://www.lenta.ru";

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect(WEB_URL).get();
        log.info("Connect to " + doc.title());
        Elements imagesURLs = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        imagesURLs.forEach(i -> {
            try {
                FileUtils.copyURLToFile(new URL(i.attr("src")),
                        new File("Stage9/src/main/java/LentaImageDownloader/lenta.ru/" +
                                i.attr("src")
                                        .substring(i.attr("src").lastIndexOf("/"))));
                log.info(i.attr("src") + " --> " + "ok");
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
    }
}
