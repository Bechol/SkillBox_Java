package array_max_value;

public class ArrayMaxValue
{
    public static int getMaxValue(int[] values)
    {
        if(values.length > 0) {
            int maxValue = Integer.MIN_VALUE;
            for (int value : values) {
                if (value > maxValue) {
                    maxValue = value;
                }
            }
            return maxValue;
        }
        return -1;
    }
}