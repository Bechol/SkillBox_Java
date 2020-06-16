import merge_sort.MergeSort;
import org.hamcrest.Matchers;
import org.junit.Test;
import quick_sort.QuickSort;

import static org.junit.Assert.assertThat;

public class MergeSortTest {

    @Test
    public void whenMergeSortArrayThenSortedArray() {
        int[] source = new int[]{5, 7, 4, 8, 3, 2};
        MergeSort.mergeSort(source, source.length);
        int[] except = new int[]{2, 3, 4, 5, 7, 8};
        assertThat(source, Matchers.is(except));
    }
}
