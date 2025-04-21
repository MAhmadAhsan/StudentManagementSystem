package Database;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseFunctions {

    /**
     * Writes the given string content to a file at the specified path.
     *
     * @param string content to write
     * @param path   path where the file should be written
     * @return true if the write operation was successful, false otherwise
     */
    public static boolean writeFile(String string, Path path) {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(string);
            return true; // Successful write
        } catch (IOException e) {
            return false; // Write failed
        }
    }

    /**
     * Reads the contents of a file from the specified path.
     *
     * @param path path of the file to read
     * @return the content of the file as a String, or null if an error occurred
     */
    public static String readFile(Path path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            return null; // Return null if read failed
        }

        return builder.toString().trim();
    }

    /**
     * Recursively deletes files and directories.
     *
     * @param path the path of the file or directory to delete
     * @return true if deletion was successful, false if any error occurred
     * @throws IOException if an I/O error occurs
     */
    public static boolean deleteFileRecursively(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    deleteFileRecursively(entry);
                }
            } catch (IOException e) {
                return false; // Return false if an error occurs during recursion
            }
        }
        Files.delete(path); // Delete the file or empty directory
        return true;
    }

    /**
     * Returns a list of child directories of the given directory.
     *
     * @param path the directory path to inspect
     * @return a string listing child directories, or an empty string if none exist
     */
    public static String allChildFile(Path path) {
        if (Files.notExists(path) || !Files.isDirectory(path)) {
            return ""; // Return empty string if path is not a directory or doesn't exist
        }

        StringBuilder classList = new StringBuilder();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            List<String> childDirectories = new ArrayList<>();
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    childDirectories.add(entry.getFileName().toString());
                }
            }

            if (!childDirectories.isEmpty()) {
                for (int i = 0; i < childDirectories.size(); i++) {
                    classList.append(childDirectories.get(i));
                    if (i < childDirectories.size() - 1) {
                        classList.append(System.lineSeparator());
                    }
                }
            } else {
                return "Nothing Exists"; // No child directories
            }
        } catch (IOException e) {
            return ""; // Return empty string on error
        }

        return classList.toString();
    }

}
