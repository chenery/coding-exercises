package fizzbuzz;

import java.util.ArrayList;
import java.util.List;

/**
 *  Write a program that prints the numbers from 1 to 100. But for multiples of three print "Fizz" instead of the
 *  number and for the multiples of five print "Buzz".
 *  For numbers which are multiples of both three and five print "FizzBuzz".
 */
public class FizzBuzz {

    static List<String> fizzBuzz(int numIterations) {

        List<String> output = new ArrayList<>();
        for (int i = 1; i <= numIterations; i++) {

            String outputLn = "";

            if (i % 3 == 0) {
                outputLn = "Fizz";
            }

            if (i % 5 == 0) {
                outputLn += "Buzz";
            }

            if (outputLn.length() == 0) {
                outputLn += i;
            }

            System.out.println(outputLn);
            output.add(outputLn);
        }

        return output;
    }

    public static void main(String[] args) {
        fizzBuzz(100);
    }
}
