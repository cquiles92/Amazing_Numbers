package numbers;

import java.util.*;

import static numbers.PrintOutFunctions.*;


public class Main {
    static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        printInstructions();
        mainLoop();
    }

    public static void mainLoop() {
        while (true) {
            long amazingNumberBase;
            long amazingNumberRange;
            String[] properties;

            System.out.print("Enter a request:");
            String[] inputs = SCANNER.nextLine().toLowerCase().split(" ");
            System.out.println();
            try {
                amazingNumberBase = Long.parseLong(inputs[0]);
                if (amazingNumberBase == 0) {
                    System.out.println("Goodbye!");
                    break;
                } else if (amazingNumberBase < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("The first parameter should be a natural number or zero.");
                System.out.println();
                continue;
            }
            if (inputs.length == 1) {
                AmazingNumber amazingNumber = new AmazingNumber(amazingNumberBase);
                amazingNumberStats(amazingNumber);
                continue;
            }

            try {
                amazingNumberRange = Long.parseLong(inputs[1]);
                if (amazingNumberRange < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("The second parameter should be a natural number.");
                System.out.println();
                continue;
            }
            if (inputs.length == 2) {
                AmazingNumber amazingNumber = new AmazingNumber(amazingNumberBase);
                listOfAmazingNumbers(amazingNumber, amazingNumberRange);
                continue;
            }
            properties = Arrays.copyOfRange(inputs, 2, inputs.length);
            AmazingNumber amazingNumber = new AmazingNumber(amazingNumberBase);
            multipleInputs(amazingNumber, amazingNumberRange, properties);
        }
    }

    public static void multipleInputs(AmazingNumber input, long range, String[] properties) {
        if (!validPropertyArray(properties)) {
            return;
        }
        Set<String> inputProperties = new HashSet<>();
        Set<String> negativeProperties = new HashSet<>();
        for (String property : properties) {
            if (property.charAt(0) == '-') {
                negativeProperties.add(property.substring(1));
            } else {
                inputProperties.add(property);
            }
        }

        long currentCounter = 0;
        long currentValue = input.getValue();
        while (currentCounter < range) {
            AmazingNumber currentNumber = new AmazingNumber(currentValue++);
            LinkedHashMap<String, Boolean> currentNumberValues = currentNumber.getPropertyMap();
            boolean valueTest = true;
            for (String property : inputProperties) {
                if (!currentNumberValues.get(property)) {
                    valueTest = false;
                    break;
                }
            }
            for (String inverseProperty : negativeProperties) {
                if (!valueTest) {
                    break;
                }
                if (currentNumberValues.get(inverseProperty)) {
                    valueTest = false;
                    break;
                }
            }
            if (valueTest) {
                StringBuilder numberInfo = numberBuilder(currentNumber);
                System.out.println(numberInfo);
                currentCounter++;
            }
        }
        System.out.println();
    }
}
