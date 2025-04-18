package DataAcessLayer;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AdminData {
    public static String readAdminPassword() {
        Path path = Paths.get("src","Database","Admin","Credentials","password.txt");
        String stringPath = path.toString();
        String line = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(stringPath))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                line = currentLine;
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file:");
            e.printStackTrace();
        }

        return line;
    }
    public static String readAdminUsername() {
        Path path = Paths.get("src","Database","Admin","Credentials","username.txt");
        String stringPath = path.toString();
        String line = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(stringPath))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                line = currentLine; // Store the last read line
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file:");
            e.printStackTrace();
        }

        return line;
    }
    public static void writeAdminPassword(String password) {
        Path path = Paths.get("src","Database","Admin","Credentials","password.txt");
        String stringPath = path.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(stringPath))) {
            writer.write(password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void writeAdminUsername(String username) {
        Path path = Paths.get("src","Database","Admin","Credentials","username.txt");
        String stringPath = path.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(stringPath))) {
            writer.write(username);
        } catch (IOException e) {
            System.out.println(e.getMessage()); // You might want to log this or handle it more gracefully in production
        }
    }
}
