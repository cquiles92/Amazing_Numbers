package numbers;

import java.util.*;

public class PrintOutFunctions {
    static void printInstructions() {
        System.out.println();
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
        System.out.println();
    }

    public static void amazingNumberStats(AmazingNumber input) {
        System.out.printf("Properties of %s\n", input.getValue());
        for (Map.Entry<String, Boolean> property : input.getPropertyMap().entrySet()) {
            String propertyName = property.getKey();
            Boolean propertyValue = property.getValue();
            String output = String.format("      %s: %b", propertyName, propertyValue);
            System.out.println(output);
        }
        System.out.println();
    }

    public static void listOfAmazingNumbers(AmazingNumber input, long range) {
        StringBuilder numberInfo;
        for (long i = 0; i < range; i++) {
            AmazingNumber currentNumber = new AmazingNumber(input.getValue() + i);
            numberInfo = numberBuilder(currentNumber);
            System.out.println(numberInfo);
        }
        System.out.println();
    }

    public static boolean validPropertyArray(String[] properties) {
        ArrayList<String> incorrectProperties = new ArrayList<>();

        for (String property : properties) {
            if (!validPropertyTest(property)) {
                incorrectProperties.add(property);
            }
        }
        if (incorrectProperties.size() == 0) {
            return mutuallyExclusiveTest(properties);
        } else if (incorrectProperties.size() > 1) {
            System.out.printf("The properties %s are wrong.\n", incorrectProperties);
            System.out.printf("Available properties: %s\n", AmazingNumber.getProperties());
            System.out.println();
            return false;
        } else {
            System.out.printf("The property %s is wrong.\n", incorrectProperties.get(0));
            System.out.printf("Available properties: %s\n", AmazingNumber.getProperties());
            System.out.println();
            return false;
        }
    }

    private static boolean mutuallyExclusiveTest(String[] properties) {
        Set<String> inputProperties = new HashSet<>();
        Set<String> negativeProperties = new HashSet<>();
//        Collections.addAll(inputProperties, properties);
        for (String property : properties) {
            if (property.charAt(0) == '-') {
                negativeProperties.add(property.substring(1));
            } else {
                inputProperties.add(property);
            }
        }

        Set<Set<String>> testHolder = new HashSet<>();
        Set<String> testOne = new HashSet<>(Arrays.asList("even", "odd"));
        Set<String> testTwo = new HashSet<>(Arrays.asList("duck", "spy"));
        Set<String> testThree = new HashSet<>(Arrays.asList("sunny", "square"));
        Set<String> testFour = new HashSet<>(Arrays.asList("happy", "sad"));

        testHolder.add(testOne);
        testHolder.add(testTwo);
        testHolder.add(testThree);
        testHolder.add(testFour);

        for (Set<String> test : testHolder) {
            if (inputProperties.containsAll(test)) {
                System.out.printf("The request contains mutually exclusive properties: %s\n", test);
                System.out.println("There are no numbers with these properties.");
                System.out.println();
                return false;
            }
        }

        for (String property : inputProperties) {
            if (negativeProperties.contains(property)) {
                System.out.printf("The request contains mutually exclusive properties: %s, -%s\n", property, property);
                System.out.println("There are no numbers with these properties.");
                System.out.println();
                return false;
            }
        }

        if (negativeProperties.containsAll(testOne)) {
            System.out.printf("The request contains mutually exclusive properties: %s, %s\n", "-even", "-odd");
            System.out.println("There are no numbers with these properties.");
            System.out.println();
            return false;
        }

        return true;
    }

    public static StringBuilder numberBuilder(AmazingNumber inputNumber) {
        StringBuilder currentString = new StringBuilder(Long.toString(inputNumber.getValue()));
        currentString.append(" is ");
        for (Map.Entry<String, Boolean> property : inputNumber.getPropertyMap().entrySet()) {
            String currentProperty = property.getKey();
            Boolean currentValue = property.getValue();
            if (currentValue) {
                currentString.append(currentProperty).append(", ");
            }
        }
        String endToDelete = ", ";
        currentString.replace(currentString.lastIndexOf(endToDelete), currentString.length(), "");

        return currentString;
    }

    public static boolean validPropertyTest(String property) {
        if (property.charAt(0) == '-') {
            return validPropertyTest(property.substring(1));
        }
        return AmazingNumber.getProperties().contains(property);
    }
}

