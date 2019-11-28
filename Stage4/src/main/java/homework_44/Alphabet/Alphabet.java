package homework_44.Alphabet;

/**
 * 4.4.1
 */
public class Alphabet {
    public static void main(String[] args) {
        for (char i = 'a'; i < 'z'; i++) {
            System.out.println(i + " code: " + Character.getNumericValue(i));
        }
    }
}
