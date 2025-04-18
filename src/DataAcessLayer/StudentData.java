package DataAcessLayer;
import Details.ContactInfo;
import Details.PersonalInfo;
import Details.StudentAcademicInfo;
import MainClasses.*;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StudentData {
    public static boolean writeNewClass(String classGrade) {
        Path classDirectoryPath = Paths.get("src", "Database", "SchoolClasses", classGrade);
        File classDirectory = classDirectoryPath.toFile();
        if (classDirectory.exists()) {
            return false;
        } else {
            return classDirectory.mkdirs();
        }
    }
    public static boolean deleteClass(String classGrade) {
        Path classDirectoryPath = Paths.get("src", "Database", "SchoolClasses", classGrade);
        File classDirectory = classDirectoryPath.toFile();

        if (!classDirectory.exists()) {
            System.out.println("Class directory does not exist.");
            return false;
        }

        try {
            deleteRecursively(classDirectory.toPath());
            return true;
        } catch (IOException e) {
            System.out.println("Failed to delete Class directory: " + e.getMessage());
            return false;
        }
    }
    public static boolean writeNewStudentDirectory(Student student) {
        String classGrade = student.getStudentAcademicInfo().getClassGrade();
        String rollNo = student.getStudentAcademicInfo().getRollNo();
        Path studentDirectoryPath = Paths.get("src", "Database", "SchoolClasses", classGrade, rollNo);
        File StudentDirectory = studentDirectoryPath.toFile();
        if(isThisStudentExists(rollNo, classGrade)) {
            return false;
        }else if (StudentDirectory.mkdirs()){
            Path studentDirPath = Paths.get("src", "Database", "SchoolClasses", classGrade, rollNo);

            try {
                Files.createDirectories(studentDirPath);
            } catch (IOException e) {
                System.out.println("Failed to create directories: " + e.getMessage());
                return false;
            }

            Path studentFilePath = studentDirPath.resolve("info.txt");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentFilePath.toFile()))) {
                writer.write(student.toString());
                return true;
            } catch (IOException e) {
                System.out.println("Failed to write student info: " + e.getMessage());
                return false;
            }
        } else{
            return false;
        }
    }
    public static boolean deleteStudentDirectory( String classGrade, String rollNo) {
        Path studentDirectoryPath = Paths.get("src", "Database", "SchoolClasses", classGrade, rollNo);
        File studentDirectory = studentDirectoryPath.toFile();

        if (!studentDirectory.exists()) {
            System.out.println("Student directory does not exist.");
            return false;
        }

        try {
            deleteRecursively(studentDirectory.toPath());
            return true;
        } catch (IOException e) {
            System.out.println("Failed to delete student directory: " + e.getMessage());
            return false;
        }
    }
    private static void deleteRecursively(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    deleteRecursively(entry);
                }
            }
        }
        Files.delete(path);
    }
    public static boolean isThisStudentExists(String rollNo, String classGrade) {

        Path classGradeDirectoryPath = Paths.get("src", "Database", "SchoolClasses", classGrade);
        Path rollNoDirectoryPath = classGradeDirectoryPath.resolve(rollNo);

        File classGradeDirectory = classGradeDirectoryPath.toFile();
        File rollNoDirectory = rollNoDirectoryPath.toFile();
        try {
            if (classGradeDirectory.exists() && classGradeDirectory.isDirectory()) {
                // Get canonical paths to resolve any symbolic links or relative paths
                File parentCanonical = classGradeDirectory.getCanonicalFile();
                File childCanonical = rollNoDirectory.getCanonicalFile();

                // Check if the child is actually within the parent directory
                return childCanonical.getPath().startsWith(parentCanonical.getPath() + File.separator)
                        && childCanonical.exists();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String getAllClasses() {
        Path classDirectoryPath = Paths.get("src", "Database", "SchoolClasses");
        File classDirectory = classDirectoryPath.toFile();

        if (!classDirectory.exists() || !classDirectory.isDirectory()) {
            return "Nothing here ;)"; // No classes exist yet
        }

        StringBuilder classList = new StringBuilder();
        File[] classFolders = classDirectory.listFiles(File::isDirectory);

        if (classFolders != null) {
            for (int i = 0; i < classFolders.length; i++) {
                classList.append(classFolders[i].getName());
                if (i < classFolders.length - 1) {
                    classList.append("\n");
                }
            }
        }

        return classList.toString();
    }
    public static String readStudentDetails(String classGrade, String rollNo) {
        Path studentFilePath = Paths.get("src", "Database", "SchoolClasses", classGrade, rollNo, "info.txt");
        File studentFile = studentFilePath.toFile();

        if (!studentFile.exists()) {
            System.out.println("Student file does not exist.");
            return null;
        }

        StringBuilder studentInfo = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                studentInfo.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Failed to read student info: " + e.getMessage());
            return null;
        }

        return studentInfo.toString();
    }


}




