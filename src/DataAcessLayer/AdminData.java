package DataAcessLayer;
import Database.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class manages the reading and writing of admin details,
 * such as the username and password, to/from the file system.
 */
public class AdminData {

    // Path to the Admin directory in the Database
    private static final Path adminPath = Paths.get("src","Database","Admin");

    /**
     * Reads the admin password from the credentials file.
     *
     * @return the admin password as a string, or an empty string if not found
     */
    public static String readAdminPassword() {
        // Resolves the path to the password file
        Path path = adminPath.resolve("Credentials","password.txt");
        return DatabaseFunctions.readFile(path);
    }

    /**
     * Reads the admin username from the credentials file.
     *
     * @return the admin username as a string, or an empty string if not found
     */
    public static String readAdminUsername() {
        // Resolves the path to the username file
        Path path = adminPath.resolve("Credentials","username.txt");
        return DatabaseFunctions.readFile(path);
    }

    /**
     * Writes the given admin password to the credentials file.
     *
     * @param password the new password to write
     * @return true if the password was successfully written, false otherwise
     */
    public static boolean writeAdminPassword(String password) {
        // Resolves the path to the password file for writing
        Path path = adminPath.resolve("Credentials","password.txt");
        return DatabaseFunctions.writeFile(password, path);
    }

    /**
     * Writes the given admin username to the credentials file.
     *
     * @param username the new username to write
     * @return true if the username was successfully written, false otherwise
     */
    public static boolean writeAdminUsername(String username) {
        // Resolves the path to the username file for writing
        Path path = adminPath.resolve("Credentials","username.txt");
        return DatabaseFunctions.writeFile(username, path);
    }
}
