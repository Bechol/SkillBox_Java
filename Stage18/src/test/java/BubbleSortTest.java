import bubble_sort.BubbleSort;
import org.junit.Test;
import org.hamcrest.Matchers;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {

    @Test
    public void whenBubbleSortArrayThenSortedArray() {
        int[] source = new int[]{14, 5, 7, 85, 25, 55, 23, 78};
        BubbleSort.sort(source);
        int[] except = new int[]{5, 7, 14, 23, 25, 55, 78, 85};
        assertThat(source, Matchers.is(except));
    }
}
