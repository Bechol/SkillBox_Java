package homework_51;

import java.util.Arrays;

/**
 * 5.1.1
 *
 * @see package-info.java
 */
public class ReverseArray {
    public static void main(String[] args) {
        String[] rainbowColors = {"Красный", "Оранжевый", "Желтый"};
        String[] reverseRainbowColors = new String[rainbowColors.length];
        for (int i = 0; i < rainbowColors.length; i++) {
            reverseRainbowColors[i] = rainbowColors[rainbowColors.length - i - 1];
        }
        System.out.println(Arrays.toString(reverseRainbowColors));
    }
}
