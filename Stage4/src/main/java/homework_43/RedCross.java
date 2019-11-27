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

                double containerAmount = Math.ceil((double) boxAmount / (double) CONTAINER_CAPACITY);
                double trackAmount = Math.ceil(containerAmount / (double) TRACK_CAPACITY);

                System.out.println("Для перевозки ящиков потребуется: " + "\n1.Грузовиков: " + trackAmount + " шт." +
                        "\n2.Контейнеров: " + containerAmount + " шт. ");

                for (int i = 1; i <= trackAmount; i++) {
                    System.out.println("Грузовик " + i + ":");
                    for (int j = 1; j <= TRACK_CAPACITY; j++) {
                        if (containerAmount > 0) {
                            System.out.println("\tКонтейнер " + j + ":");
                            containerAmount--;
                        } else {
                            break;
                        }
                        for (int k = 1; k <= CONTAINER_CAPACITY; k++) {
                            if (boxAmount > 0) {
                                System.out.println("\t\tЯщик " + k);
                                boxAmount--;
                            } else {
                                break;
                            }
                        }
                    }
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
