import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileEncryptDecrypt {
    public static String encrypt(String text, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((ch - base + shift) % 26 + base);
            }
            encryptedText.append(ch);
        }
        return encryptedText.toString();
    }
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift); 
    }


    public static String readFile(String fileName) throws FileNotFoundException {
        StringBuilder fileContent = new StringBuilder();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            fileContent.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return fileContent.toString();
    }

    public static void writeFile(String fileName, String content) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(content);
        writer.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to encrypt or decrypt? (E/D): ");
        String choice = scanner.nextLine().toUpperCase();

        
        System.out.print("Enter the file name or path: ");
        String fileName = scanner.nextLine();
        System.out.print("Enter the shift value (1-25): ");
        int shift = scanner.nextInt();
        scanner.nextLine(); 

        try {
            String fileContent = readFile(fileName);
            String result;
            if (choice.equals("E")) {
                result = encrypt(fileContent, shift);
            } else {
                result = decrypt(fileContent, shift);
            }
            String outputFileName = choice.equals("E") ? "encrypted_" + fileName : "decrypted_" + fileName;
            writeFile(outputFileName, result);

            System.out.println("Operation completed. The result has been saved to " + outputFileName);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }

        scanner.close();
    }
}
