package Functions;

import java.util.Scanner;

public class Input {
    public static int intRangeInput(int input, int min, int max) {
        if (input < min || input > max) {
            throw new IllegalArgumentException("Valid range of input is " + min + " to " + max);
        } else{
            return input;
        }
    }
}
