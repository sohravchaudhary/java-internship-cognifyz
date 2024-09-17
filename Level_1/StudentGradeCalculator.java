import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of grades: ");
        int numGrades = scanner.nextInt();

        int[] grades = new int[numGrades];

        for (int i = 0; i < numGrades; i++) {
            System.out.print("Enter grade #" + (i + 1) + ": ");
            grades[i] = scanner.nextInt();
        }


        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }


        double average = (double) sum / numGrades;


        System.out.println("The average grade is: " + average);

        scanner.close(); 
    }
}