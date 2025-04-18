import java.io.*;
import java.util.Scanner;

public class FileHandler {

    // Method to write content to a file
    public static void writeFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read content from a file
    public static void readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Reading file:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to append content to an existing file
    public static void appendToFile(String filename, String newText) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(newText);
            System.out.println("Text appended successfully.");
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "example.txt";

        System.out.println("1. Write to file");
        System.out.println("2. Read file");
        System.out.println("3. Append to file");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter text to write: ");
                String textToWrite = scanner.nextLine();
                writeFile(filename, textToWrite);
                break;
            case 2:
                readFile(filename);
                break;
            case 3:
                System.out.print("Enter text to append: ");
                String textToAppend = scanner.nextLine();
                appendToFile(filename, "\n" + textToAppend);
                break;
            default:
                System.out.println("Invalid option.");
        }

        scanner.close();
    }
}
