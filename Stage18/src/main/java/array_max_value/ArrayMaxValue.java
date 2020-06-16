package array_max_value;

public class ArrayMaxValue
{
    public static int getMaxValue(int[] values)
    {
        int maxValue = Integer.MIN_VALUE;
        for(int value : values)
        {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }
}