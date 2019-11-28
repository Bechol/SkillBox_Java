package homework_44.StringExperiments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 4.4.2
 */
public class StringExperiments {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        String[] arr = text.split(", ");
        int sum = 0;
        for (String str : arr) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(str);
            if (str.contains("Вася") || str.contains("Маша"))
                if (matcher.find())
                    sum += Integer.valueOf(str.substring(matcher.start(), matcher.end()));
        }
        System.out.println(sum);
    }
}
