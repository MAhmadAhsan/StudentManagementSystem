package DataAcessLayer;
import Database.DatabaseFunctions;
import Details.ContactInfo;
import Details.PersonalInfo;
import Details.StudentAcademicInfo;
import MainClasses.*;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StudentData {
    public static final Path schoolClasssesPath = Paths.get("src","Database","SchoolClasses");

    public static boolean writeNewClassGrade(String classGrade) {
        // Resolves the path to the class grade directory
        Path classGradeDirPath = schoolClasssesPath.resolve(classGrade);

        // Converts the Path object to a File object for checking existence
        File classDirectory = classGradeDirPath.toFile();

        // Checks if the directory exists
        if (classDirectory.exists()) {
            return false;
        } else {
            return classDirectory.mkdirs();
        }
    }

    /**
     * Deletes the directory corresponding to the given class grade and its contents.
     *
     * @param classGrade the name of the class grade directory to delete
     * @return true if the class grade directory was successfully deleted, false otherwise
     */
    public static boolean deleteClassGrade(String classGrade) {
        // Resolves the path to the class grade directory
        Path classGradeDirPath = schoolClasssesPath.resolve(classGrade);

        // Converts the Path object to a File object for checking existence
        File classGradeDir = classGradeDirPath.toFile();

        // Checks if the directory exists
        if (classGradeDir.exists()) {
            try {
                // Attempts to delete the directory and its contents recursively
                return DatabaseFunctions.deleteFileRecursively(classGradeDirPath);
            } catch (Exception e) {
                // Returns false if an error occurs during the deletion process
                return false;
            }
        } else {
            // Returns false if the directory does not exist
            return false;
        }
    }

    public static boolean writeNewStudentDirectory(Student student) {
        String classGrade = student.getStudentAcademicInfo().getClassGrade();
        String rollNo = student.getStudentAcademicInfo().getRollNo();

        Path studentDirectoryPath = schoolClasssesPath.resolve(classGrade, rollNo);

        File StudentDirectory = studentDirectoryPath.toFile();
        if(StudentDirectory.exists()) {
            return false;
        }else if (StudentDirectory.mkdirs()){
            Path studentFilePath = studentDirectoryPath.resolve("info.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentFilePath.toFile()))) {
                writer.write(student.toString());
                return true;
            } catch (IOException e) {
                return false;
            }
        } else{
            return false;
        }
    }

    public static boolean deleteStudentDirectory( String classGrade, String rollNo) {
        // Resolves the path to the class grade directory
        Path studentDirPath = schoolClasssesPath.resolve(classGrade, rollNo);

        // Converts the Path object to a File object for checking existence
        File studentDir = studentDirPath.toFile();

        // Checks if the directory exists
        if (studentDir.exists()) {
            try {
                // Attempts to delete the directory and its contents recursively
                return DatabaseFunctions.deleteFileRecursively(studentDirPath);
            } catch (Exception e) {
                // Returns false if an error occurs during the deletion process
                return false;
            }
        } else {
            // Returns false if the directory does not exist
            return false;
        }
    }
    public static String getAllClasses() {
        Path classDirectoryPath = schoolClasssesPath;
        File classDirectory = classDirectoryPath.toFile();

        if (!classDirectory.exists() || !classDirectory.isDirectory()) {
            return null; // No classes exist yet
        }
        return DatabaseFunctions.allChildFile(classDirectoryPath);
    }
    public static String readStudentDetails(String classGrade, String rollNo) {
        Path studentFilePath = schoolClasssesPath.resolve(classGrade, rollNo,"info.txt");
        File studentFile = studentFilePath.toFile();

        if (!studentFile.exists()) {
            return null;
        }
        return DatabaseFunctions.readFile(studentFilePath);
    }
}




