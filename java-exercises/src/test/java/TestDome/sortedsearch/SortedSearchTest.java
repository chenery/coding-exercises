package TestDome.sortedsearch;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 *
 */
public class SortedSearchTest {

    @Test
    public void givenTest() {
        assertThat(SortedSearch.countNumbers(new int[]{1, 3, 5, 7}, 4)).isEqualTo(2);
    }

    @Test
    public void countNumbers_smallArray() {
        assertThat(SortedSearch.countNumbers(new int[]{4, 5}, 4)).isEqualTo(0);
        assertThat(SortedSearch.countNumbers(new int[]{4, 5}, 5)).isEqualTo(1);
        assertThat(SortedSearch.countNumbers(new int[]{4, 5}, 6)).isEqualTo(2);
    }

    @Test
    public void countNumbers_anotherSmallArray() {
        assertThat(SortedSearch.countNumbers(new int[]{1, 3, 5}, 0)).isEqualTo(0);
        assertThat(SortedSearch.countNumbers(new int[]{1, 3, 5}, 1)).isEqualTo(0);
        assertThat(SortedSearch.countNumbers(new int[]{1, 3, 5}, 2)).isEqualTo(1);
        assertThat(SortedSearch.countNumbers(new int[]{1, 3, 5}, 3)).isEqualTo(1);
        assertThat(SortedSearch.countNumbers(new int[]{1, 3, 5}, 4)).isEqualTo(2);
        assertThat(SortedSearch.countNumbers(new int[]{1, 3, 5}, 5)).isEqualTo(2);
        assertThat(SortedSearch.countNumbers(new int[]{1, 3, 5}, 6)).isEqualTo(3);
    }

    @Test
    public void countNumbers_smallDuplicateValuesArray() {
        assertThat(SortedSearch.countNumbers(new int[]{4, 4}, 4)).isEqualTo(0);
        assertThat(SortedSearch.countNumbers(new int[]{4, 4}, 5)).isEqualTo(2);
        assertThat(SortedSearch.countNumbers(new int[]{4, 4}, 6)).isEqualTo(2);
    }
}
