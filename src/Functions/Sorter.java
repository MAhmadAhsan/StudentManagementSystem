package Functions;

import java.util.Arrays;

public class Sorter {
    public static String inAscendingOrder(String input) {
        // Split the input string by newline and convert each part to an integer
        String[] stringNumbers = input.split("\n");
        int[] numbers = new int[stringNumbers.length];

        for (int i = 0; i < stringNumbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i]);
        }

        // Sort the array in ascending order
        Arrays.sort(numbers);

        // Convert the sorted array back to a string with newlines
        StringBuilder result = new StringBuilder();
        for (int num : numbers) {
            result.append(num).append("\n");
        }

        // Return the result as a string
        return result.toString().trim();  // Remove the last newline
    }
}
