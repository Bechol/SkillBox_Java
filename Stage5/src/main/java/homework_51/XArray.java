package homework_51;

/**
 * 5.1.3
 *
 * @see package-info.java
 */
public class XArray {

    private static final int SQUARE_SIZE = 7;

    public static void main(String[] args) {
        String[][] arr = new String[SQUARE_SIZE][SQUARE_SIZE];
        for (int i = 0; i < SQUARE_SIZE; i++) {
            for (int j = 0; j < SQUARE_SIZE; j++) {
                if (i == j || (i + j + 1) == SQUARE_SIZE) {
                    arr[i][j] = "x";
                } else {
                    arr[i][j] = " ";
                }
                System.out.print(arr[i][j]);

            }
            System.out.println("\n");
        }
    }
}
