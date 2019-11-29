package homework_44.StringExperiments;

/**
 * 4.4.2
 */
public class StringExperiments {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        int vasiaSalary = Integer.valueOf(text.substring(text.indexOf("5000"), text.indexOf("рублей")).trim());
        int mashaSalary = Integer.valueOf(text.substring(text.indexOf("30000"), text.lastIndexOf("рублей")).trim());
        System.out.println("Сумма заработка Васи и Маши: " + (vasiaSalary + mashaSalary));
    }
}
