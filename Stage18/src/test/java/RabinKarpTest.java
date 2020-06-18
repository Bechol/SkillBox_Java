import org.hamcrest.Matchers;
import org.junit.Test;
import rabin_karp.RabinKarpExtended;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;

public class RabinKarpTest {

    @Test
    public void whenRabinKarpFindSubStringThenReturnArrayOfIndexes() {
        RabinKarpExtended rabinKarpExtended = new RabinKarpExtended("Skillbox");
        List<Integer> actualIndexes = rabinKarpExtended.search("box");
        List<Integer> expectedIndexes = Collections.singletonList(5);
        assertThat(actualIndexes, Matchers.is(expectedIndexes));
    }
}
