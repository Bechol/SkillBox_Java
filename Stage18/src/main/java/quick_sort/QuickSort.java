package quick_sort;

public class QuickSort
{
    public static void sort(int[] array)
    {
        if(array.length <= 1) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int from, int to)
    {
        if(from < to)
        {
            int pivot = partition(array, from, to);
            sort(array, from, pivot - 1);
            sort(array, pivot + 1, to);
        }
    }

    private static int partition(int[] array, int from, int to)
    {
        int pivot = array[from];
        int i = from - 1;
        int j = to + 1;
        while (i < j) {
            for (i++; array[i] < pivot; i++);
            for (j--; array[j] > pivot; j--);
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        return j;
    }

}
