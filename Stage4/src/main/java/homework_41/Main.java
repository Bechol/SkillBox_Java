package homework_41;

public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.count += 7843;

        System.out.println(new Main().sumDigits(7345));
    }

    public Integer sumDigits(Integer number) {
        int sum = 0;
        for (int i = 0; i < number.toString().length(); i++) {
            sum += Character.getNumericValue(number.toString().charAt(i));
        }
        return sum;
    }
}
