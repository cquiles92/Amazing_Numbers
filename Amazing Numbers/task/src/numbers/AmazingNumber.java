package numbers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

public class AmazingNumber {
    private final long value;

    private final LinkedHashMap<String, Boolean> propertyMap;

    private static final List<String> properties = Arrays.asList("buzz", "duck", "palindromic", "gapful",
            "spy", "square", "sunny", "jumping", "happy", "sad", "even", "odd");

    public AmazingNumber(long value) {
        this.value = value;
        propertyMap = new LinkedHashMap<>();

        numberPropertyList();
    }

    private void numberPropertyList() {
        isBuzzNumber(this.value);
        isDuckNumber(this.value);
        isPalindromeNumber(this.value);
        isGapfulNumber(this.value);
        isSpyNumber(this.value);
        isSquare(this.value);
        isSunny(this.value);
        isJumping(this.value);
        isHappyOrSad(this.value);
        isEven(this.value);
        isOdd(this.value);
    }

    private void isBuzzNumber(long number) {
        boolean evenlyDivisible = evenlyDividesBySeven(number);
        boolean lastDigitSeven = endsWithSeven(number);
        propertyMap.put("buzz", (evenlyDivisible || lastDigitSeven));
    }

    private boolean evenlyDividesBySeven(long number) {
        return number % 7 == 0;
    }

    private boolean endsWithSeven(long number) {
        number %= 10;
        return number % 7 == 0 && number != 0;
    }

    private void isDuckNumber(long number) {
        boolean duckTest = false;
        while (number > 0) {
            if (number % 10 == 0) {
                duckTest = true;
                break;
            }
            number /= 10;
        }
        propertyMap.put("duck", duckTest);
    }

    private void isPalindromeNumber(long number) {
        StringBuilder numberToString = new StringBuilder(String.valueOf(number));
        numberToString.reverse();
        propertyMap.put("palindromic", String.valueOf(number).equals(numberToString.toString()));
    }

    private void isGapfulNumber(long number) {
        if (number < 100) {
            propertyMap.put("gapful", false);
            return;
        }

        String numberAsString = String.valueOf(number);
        int front = 10 * Integer.parseInt(numberAsString.substring(0, 1));
        int end = Integer.parseInt(numberAsString.substring(numberAsString.length() - 1));

        propertyMap.put("gapful", number % (front + end) == 0);
    }

    private void isSpyNumber(long number) {
        long sum = 0;
        long multiplier = 1;
        while (number > 0) {
            int digit = (int) (number % 10);
            sum += digit;
            multiplier *= digit;
            number /= 10;
        }

        propertyMap.put("spy", multiplier == sum);
    }

    private void isEven(long number) {
        propertyMap.put("even", (number % 2) == 0);
    }

    private void isOdd(long number) {
        propertyMap.put("odd", (number % 2) == 1);
    }

    private void isSquare(long number) {
        long square = (long) Math.sqrt((double) number);
        propertyMap.put("square", (square * square) == number);
    }

    private void isSunny(long number) {
        long next = (long) Math.sqrt((double) number + 1);
        boolean sunnyTest = (next * next) == (number + 1);
        propertyMap.put("sunny", sunnyTest);
    }

    private void isJumping(long number) {
        if (number < 10) {
            propertyMap.put("jumping", true);
            return;
        }
        String looper = String.valueOf(number);

        for (int i = 0; i < looper.length() - 1; i++) {
            int test = Integer.parseInt(looper.substring(i, i + 1));
            int test2 = Integer.parseInt(looper.substring(i + 1, i + 2));
            if (test + 1 == test2 || test - 1 == test2) {

            } else {
                propertyMap.put("jumping", false);
                return;
            }
            propertyMap.put("jumping", true);
        }
    }

    private void isHappyOrSad(long number) {
        HashSet<Long> seenNumbers = new HashSet<>();
        while (number != 1) {
            long current = number;
            long sum = 0;
            while (current != 0) {
                sum += (current % 10) * (current % 10);
                current /= 10;
            }
            if (seenNumbers.contains(number)) {
                propertyMap.put("happy", false);
                propertyMap.put("sad", true);
                return;
            } else {
                seenNumbers.add(number);
                number = sum;
            }
        }
        propertyMap.put("happy", true);
        propertyMap.put("sad", false);
    }

    public long getValue() {
        return value;
    }

    static public List<String> getProperties() {
        return properties;
    }

    public LinkedHashMap<String, Boolean> getPropertyMap() {
        return propertyMap;
    }
}
