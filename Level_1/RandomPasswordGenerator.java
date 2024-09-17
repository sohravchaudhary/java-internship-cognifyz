import java.util.Random;
import java.util.Scanner;

public class RandomPasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();


        System.out.print("Enter the desired password length: ");
        int length = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Include numbers? (y/n): ");
        boolean includeNumbers = scanner.nextLine().equalsIgnoreCase("y");

        System.out.print("Include lowercase letters? (y/n): ");
        boolean includeLowercase = scanner.nextLine().equalsIgnoreCase("y");

        System.out.print("Include uppercase letters? (y/n): ");
        boolean includeUppercase = scanner.nextLine().equalsIgnoreCase("y");

        System.out.print("Include special characters? (y/n): ");
        boolean includeSpecialChars = scanner.nextLine().equalsIgnoreCase("y");

        String charSet = "";
        if (includeNumbers) charSet += "0123456789";
        if (includeLowercase) charSet += "abcdefghijklmnopqrstuvwxyz";
        if (includeUppercase) charSet += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (includeSpecialChars) charSet += "!@#$%^&*()_-+={}[]|;:'\",.<>/?";

  
        if (charSet.isEmpty()) {
            System.out.println("Error: At least one character type must be selected.");
            return;
        }

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charSet.length());
            password.append(charSet.charAt(randomIndex));
        }

        System.out.println("Generated Password: " + password.toString());

        scanner.close();
    }
}