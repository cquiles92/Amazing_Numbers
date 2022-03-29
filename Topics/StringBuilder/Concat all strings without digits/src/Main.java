import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        // write your code with StringBuilder here
        StringBuilder output = new StringBuilder();
        for (String individual : strings) {
            for (int i = 0; i < individual.length(); i++) {
                if (Character.isDigit(individual.charAt(i))) {
                    continue;
                }
                output.append(individual.charAt(i));
            }
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}