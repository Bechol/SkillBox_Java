package JsonParser;

import JsonParser.pojo.Line;
import JsonParser.pojo.Passage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Домашняя работа 9.14.
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
public class Main {
    /**
     * Коллекция metro.
     * Для хранения данных о станциях, линиях и переходах метро.
     */
    private static Map<String, Object> metro = new LinkedHashMap<>();
    /**
     * Сет уникальных номеров линий uniqueLineNumbers.
     * Для сохранения уникальных номеров линий в порядке добавления.
     */
    private static Set<String> uniqueLineNumbers = new LinkedHashSet<>();
    /**
     * Коллекция stations.
     * Для сохранения перечня наименований станций для каждой линии.
     */
    private static Map<String, List<String>> stations = new LinkedHashMap<>();
    /**
     * Коллеция passageList.
     * Хранит информацию о переходах между станциями.
     */
    private static List<List<Passage>> passageList = new ArrayList<>();
    /**
     * Сет lineSet.
     * Сет линий метро.
     */
    private static Set<Line> lineSet = new LinkedHashSet<>();
    /**
     * Адрес страницы в интернет для чтения данных.
     */
    private static final String WEB_URL = "https://ru.wikipedia.org/wiki/" +
            "%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_" +
            "%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_" +
            "%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0";

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect(WEB_URL).maxBodySize(0).get();
        Elements trs = doc.select(".standard.sortable tr");
        //парсинг уникальных номеров линий
        trs.stream().filter(tr -> !tr.select("td:nth-child(1) span:nth-child(1)").text().isEmpty())
                .forEach(tr -> uniqueLineNumbers.add(tr.select("td:nth-child(1) span:nth-child(1)").text()));

        uniqueLineNumbers.forEach(line -> {
            List<String> stationList = new ArrayList<>();
            //парсинг станций
            trs.parallelStream().filter(tr -> tr.select("td:nth-child(1) span:nth-child(1)").text().equals(line))
                    .forEach(tr -> {
                        tr.select("td:nth-child(2) small").remove();
                        stationList.add(tr.select("td:nth-child(2)").text());
                    });
            stations.put(line, stationList);
            //парсинг линий
            trs.parallelStream().filter(tr -> tr.select("td:nth-child(1) span:nth-child(1)")
                    .text().equals(line)).findFirst().ifPresent(x -> lineSet.add(new Line(
                    line,
                    x.select("td:nth-child(1) span:nth-child(2)").attr("title"),
                    getMetroLineColor(x.select("td:nth-child(1)").attr("style")),
                    stationList.size())));
        });
        //парсинг переходов
        trs.parallelStream().filter(tr -> !tr.select("td:nth-child(4)").text().isEmpty())
                .forEach(tr -> {
                    List<Passage> pList = new ArrayList<>();
                    List<Element> elList = new ArrayList<>(tr.select("td:nth-child(4) span"));
                    for (int i = 0; i < elList.size() - 1; i += 2) {
                        pList.add(new Passage(elList.get(i).text(), substringPassageStation(elList.get(i + 1)
                                .attr("title"))));
                    }
                    passageList.add(pList);
                });

        metro.put("stations", stations);
        metro.put("connections", passageList);
        metro.put("lines", lineSet);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("metro.json"), metro);
    }

    /**
     * Метод String getMetroLineColor(String tdStyle).
     * Возвращает строку с HEX кодом цвета линии метро.
     * @param tdStyle аттрибут style.
     * @return цвет станции в HEX формате.
     */
    private static String getMetroLineColor(String tdStyle) {
        return tdStyle.isEmpty() ? "undefined" : tdStyle.substring(tdStyle.indexOf("#"));
    }

    /**
     * Метод String substringPassageStation(String spanTitleAttribute).
     * Возвращает станцию перехода.
     * @param spanTitleAttribute - аттрибут title.
     * @return
     */
    private static String substringPassageStation(String spanTitleAttribute) {
        return spanTitleAttribute.isEmpty() ? "неопределена" : spanTitleAttribute.substring(
                spanTitleAttribute.indexOf("ю ") + 1);
    }
}