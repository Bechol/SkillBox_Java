package merge_sort;

public class MergeSort {
    public static void mergeSort(int[] array, int n) {
        if (n < 2) {
            return;
        }
        int middle = n / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[n - middle];

        for (int i = 0; i < middle; i++) {
            leftArray[i] = array[i];
        }
        for (int i = middle; i < n; i++) {
            rightArray[i - middle] = array[i];
        }
        mergeSort(leftArray, middle);
        mergeSort(rightArray, n - middle);

        merge(array, leftArray, rightArray, middle, n - middle);
    }

    public static void merge(int[] sourceArray, int[] leftArray, int[] rightArray, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftArray[i] <= rightArray[j]) {
                sourceArray[k++] = leftArray[i++];
            } else {
                sourceArray[k++] = rightArray[j++];
            }
        }
        while (i < left) {
            sourceArray[k++] = leftArray[i++];
        }
        while (j < right) {
            sourceArray[k++] = rightArray[j++];
        }
    }
}
