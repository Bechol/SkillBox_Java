package homework_44.StringExperiments;

/**
 * 4.4.2
 */
public class StringExperiments {
    private static String first = "5000";
    private static String second = "30000";
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        int vasiaSalary = Integer.parseInt(text.substring(text.indexOf(first), text.indexOf(second)).trim());
        int mashaSalary = Integer.parseInt(text.substring(text.indexOf("30000"), text.lastIndexOf("рублей")).trim());
        System.out.println("Сумма заработка Васи и Маши: " + (vasiaSalary + mashaSalary));
    }
}
