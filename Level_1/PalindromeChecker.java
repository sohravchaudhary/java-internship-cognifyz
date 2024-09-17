import java.util.Scanner;

public class PalindromeChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a word or phrase: ");
        String input = scanner.nextLine();
        

        String cleanedInput = input.replaceAll("\\s+", "").toLowerCase();
        

        String reversedInput = new StringBuilder(cleanedInput).reverse().toString();
        

        if (cleanedInput.equals(reversedInput)) {
            System.out.println("The input is a palindrome.");
        } else {
            System.out.println("The input is not a palindrome.");
        }
        
        scanner.close();
    }
}
