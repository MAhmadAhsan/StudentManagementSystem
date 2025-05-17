package Database;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseFunctions {
    public static boolean writeFile(String string, Path path) {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(string);
            return true; // Successful write
        } catch (IOException e) {
            return false; // Write failed
        }
    }

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
