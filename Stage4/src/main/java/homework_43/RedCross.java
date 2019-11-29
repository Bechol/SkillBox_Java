package homework_43;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RedCross {
    private static final int TRACK_CAPACITY = 12;
    private static final int CONTAINER_CAPACITY = 27;

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("Введите количество ящиков с гуманитарной помощью: ");
                int boxAmount = (new Scanner(System.in)).nextInt();

                if (!checkInput(boxAmount)) {
                    printAlert("значение должно быть больше нуля.");
                    continue;
                }

                for (int i = 0; i < boxAmount; i++) {
                    if (i % (TRACK_CAPACITY * CONTAINER_CAPACITY) == 0) {
                        System.out.println("Грузовик " + (i / TRACK_CAPACITY / CONTAINER_CAPACITY + 1) + ":");
                    }
                    if (i % CONTAINER_CAPACITY == 0) {
                        System.out.println("\tКонтейнер " + (i / CONTAINER_CAPACITY + 1) + ":");
                    }
                    System.out.println("\t\tЯщик " + (i + 1));
                }
            } catch (InputMismatchException ime) {
                printAlert("значение должно быть целым числом.");
            }
        }
    }


    private static boolean checkInput(int value) {
        return value > 0;
    }

    private static void printAlert(String str) {
        System.out.println("!> " + str);
    }

}
