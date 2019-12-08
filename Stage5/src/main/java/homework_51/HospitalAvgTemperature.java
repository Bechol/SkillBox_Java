package homework_51;

import java.util.Random;

/**
 * 5.1.2
 *
 * @see package-info.java
 */
public class HospitalAvgTemperature {
    private static int amount = 30, low = 32, high = 40;

    public static void main(String[] args) {
        int tempSum = 0;
        int count = 0;
        double[] temperatureArray = initTemperatureArray(amount, low, high);
        for (int i = 0; i < temperatureArray.length; i++) {
            tempSum += temperatureArray[i];
            if (temperatureArray[i] > 36.2 && temperatureArray[i] < 36.9)
                count++;
        }
        System.out.println("Средняя температура по больнице: " + tempSum / amount);
        System.out.println("Количество здоровых пациентов: " + count);
    }

    /**
     * Метод для генерации массива температур пациентов в диапазоне.
     *
     * @param amount - количество значений (размер массива).
     * @param low    минимальное значение температуры.
     * @param high   максимальное значение температуры.
     * @return массив с температурами пациентов в диапазоне.
     */
    private static double[] initTemperatureArray(int amount, int low, int high) {
        double[] result = new double[amount];
        for (int i = 0; i < amount; i++) {
            result[i] = low + Math.random() * (high - low);
        }
        return result;
    }
}
