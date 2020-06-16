import org.junit.Test;
import org.hamcrest.Matchers;
import quick_sort.QuickSort;

import static org.junit.Assert.assertThat;

public class QuickSortTest {

    @Test
    public void whenQuickSortArrayThenSortedArray() {
        int[] source = new int[]{5, 7, 4, 8, 3, 2};
        QuickSort.sort(source);
        int[] except = new int[]{2, 3, 4, 5, 7, 8};
        assertThat(source, Matchers.is(except));
    }
}
