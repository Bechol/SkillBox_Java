package homework_45.TelNum;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 4.5.4
 */
public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите номер телефона: ");
            String telNum = new Scanner(System.in).nextLine();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(telNum);
            StringBuilder sb = new StringBuilder();
            while (matcher.find())
                sb.append(telNum.substring(matcher.start(), matcher.end()));
            try {
                System.out.println(sb.toString().substring(0, 11));
            } catch (StringIndexOutOfBoundsException ex) {
                System.out.println("!> Слишком коротуий номер.");
            }
        }
    }
}
