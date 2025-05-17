package DataAcessLayer;
import Database.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AdminData {

    // Path to the Admin directory in the Database
    private static final Path adminPath = Paths.get("src","Database","Admin");

    public static String readAdminPassword() {
        // Resolves the path to the password file
        Path path = adminPath.resolve("Credentials","password.txt");
        return DatabaseFunctions.readFile(path);
    }

    public static String readAdminUsername() {
        // Resolves the path to the username file
        Path path = adminPath.resolve("Credentials","username.txt");
        return DatabaseFunctions.readFile(path);
    }

    public static boolean writeAdminPassword(String password) {
        // Resolves the path to the password file for writing
        Path path = adminPath.resolve("Credentials","password.txt");
        return DatabaseFunctions.writeFile(password, path);
    }

    public static boolean writeAdminUsername(String username) {
        // Resolves the path to the username file for writing
        Path path = adminPath.resolve("Credentials","username.txt");
        return DatabaseFunctions.writeFile(username, path);
    }
}
