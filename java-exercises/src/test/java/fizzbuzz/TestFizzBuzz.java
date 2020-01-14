package fizzbuzz;

import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 *  Show that fizz buzz works for a limited number of iterations
 */
public class TestFizzBuzz {

    @Test
    public void testFizzBuzz() {
        List<String> output = FizzBuzz.fizzBuzz(15);
        assertThat(output).containsExactly("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11",
                "Fizz", "13", "14", "FizzBuzz");
    }
}
