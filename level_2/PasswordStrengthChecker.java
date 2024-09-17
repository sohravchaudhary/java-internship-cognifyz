import java.util.Scanner;

public class PasswordStrengthChecker {

    public static String checkPasswordStrength(String password) {
        int length = password.length();
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (int i = 0; i < length; i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) hasUpperCase = true;
            if (Character.isLowerCase(ch)) hasLowerCase = true;
            if (Character.isDigit(ch)) hasDigit = true;
            if ("!@#$%^&*()-+".indexOf(ch) != -1) hasSpecialChar = true;
        }

        
        if (length >= 8 && hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar) {
            return "Strong";
        } else if (length >= 6 && ((hasUpperCase && hasLowerCase) || (hasLowerCase && hasDigit) || (hasUpperCase && hasDigit))) {
            return "Medium";
        } else {
            return "Weak";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        String strength = checkPasswordStrength(password);
        System.out.println("Your password strength is: " + strength);

        scanner.close();
    }
}
