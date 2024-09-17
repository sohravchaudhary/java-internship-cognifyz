import java.util.InputMismatchException;
import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double temperature = 0;
        boolean validInput = false;


        while (!validInput) {
            try {
                System.out.print("Enter the temperature value: ");
                temperature = scanner.nextDouble();
                validInput = true; 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value for the temperature.");
                scanner.next(); 
            }
        }

        char unit = ' ';
        boolean validUnit = false;

        while (!validUnit) {
            System.out.print("Enter the unit of temperature (C for Celsius, F for Fahrenheit): ");
            unit = scanner.next().charAt(0);

            if (unit == 'C' || unit == 'c' || unit == 'F' || unit == 'f') {
                validUnit = true;
            } else {
                System.out.println("Invalid unit. Please enter 'C' for Celsius or 'F' for Fahrenheit.");
            }
        }

        if (unit == 'C' || unit == 'c') {
            double fahrenheit = (temperature * 9/5) + 32;
            System.out.println(temperature + "°C is equal to " + fahrenheit + "°F");
        } else if (unit == 'F' || unit == 'f') {
            double celsius = (temperature - 32) * 5/9;
            System.out.println(temperature + "°F is equal to " + celsius + "°C");
        }

        scanner.close();
    }
}

