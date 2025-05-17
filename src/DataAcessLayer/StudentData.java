package DataAcessLayer;
import Database.DatabaseFunctions;
import MainClasses.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


import static Database.DatabaseFunctions.writeFile;

public class StudentData {
    private static final Path schoolClasssesPath = Paths.get("src","Database","SchoolClasses");

    public static boolean writeNewClassGrade(String classGrade) {
        // Resolves the path to the class grade directory
        Path classGradeDirPath = schoolClasssesPath.resolve(classGrade);

        // Checks if the directory exists
        if (classGradeDirPath.toFile().exists()) {
            return false;
        } else {
            return classGradeDirPath.toFile().mkdirs();
        }
    }
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
    public static boolean writeNewStudent(Student student) {
        String classGrade = student.getStudentAcademicInfo().getClassGrade();
        String rollNo = student.getStudentAcademicInfo().getRollNo();

        Path studentDirectoryPath = schoolClasssesPath.resolve(classGrade, rollNo);

        File StudentDirectory = studentDirectoryPath.toFile();

        if(StudentDirectory.exists()) {
            return false;
        }else if (StudentDirectory.mkdirs()){
            Path studentInfoPath = studentDirectoryPath.resolve("info.txt");

            Path studentUsernamePath = studentDirectoryPath.resolve("username.txt");
            return writeFile(student.toString(), studentInfoPath) && writeStudentPassword(student.getCredentials().getPassword(), classGrade, rollNo) && writeStudentUsername(student.getCredentials().getUsername(), classGrade, rollNo);
        } else{
            return false;
        }
    }
    public static String readStudentPassword(String classGrade, String rollNo) {
        Path studentPasswordPath = schoolClasssesPath.resolve(classGrade, rollNo, "password.txt");
        return DatabaseFunctions.readFile(studentPasswordPath);
    }
    public static String readStudentUsername(String classGrade, String rollNo) {
        Path studentPasswordPath = schoolClasssesPath.resolve(classGrade, rollNo, "username.txt");
        return DatabaseFunctions.readFile(studentPasswordPath);
    }
    public static boolean writeStudentPassword(String password ,String classGrade, String rollNo) {
        Path studentPasswordPath = schoolClasssesPath.resolve(classGrade, rollNo,"password.txt");
        return DatabaseFunctions.writeFile(password, studentPasswordPath);
    }
    public static boolean writeStudentUsername(String username,String classGrade, String rollNo) {
        Path studentUsernamePath = schoolClasssesPath.resolve(classGrade, rollNo,"username.txt");
        return DatabaseFunctions.writeFile(username, studentUsernamePath);
    }
    public static boolean deleteStudentDir( String classGrade, String rollNo) {
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
    public static String readAllClasses() {
        Path classDirectoryPath = schoolClasssesPath;
        File classDirectory = classDirectoryPath.toFile();

        if (!(classDirectory.exists() && classDirectory.isDirectory())) {
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
    public static String readStudentsInClass(String classGrade) {
        Path classDirectoryPath = schoolClasssesPath.resolve(classGrade);
        File classDirectory = classDirectoryPath.toFile();
        if (!classDirectory.exists() || !classDirectory.isDirectory()) {
            return null; // No classes exist yet
        }
        return DatabaseFunctions.allChildFile(classDirectoryPath);
    }
    public static boolean writeNewAttendance(Map<String, String> attendance, String classGrade, String course, String date) {
        // Path to the directory where attendance file will be stored
        Path attendancePath = Paths.get("src","Database", "Attendances");
        Path dirPath = attendancePath.resolve(classGrade).resolve(course);

        // Full path to the attendance file
        Path attendanceFilePath = dirPath.resolve(date + ".csv");

        // Check if file already exists
        if (Files.exists(attendanceFilePath)) {
            return false;
        }

        try {
            // Create the necessary directories (up-to-date folder)
            Files.createDirectories(dirPath);

            // Write to file
            try (BufferedWriter writer = Files.newBufferedWriter(attendanceFilePath)) {
                writer.write("Roll no,Attendance\n"); // Header
                for (Map.Entry<String, String> entry : attendance.entrySet()) {
                    writer.write(entry.getKey() + "," + entry.getValue() + "\n");
                }
            }

            return true;

        } catch (IOException e) {
            return false;
        }
    }
    public static String readAttendance(String classGrade, String course, String date) {
        // Path to the directory where attendance file will be stored
        Path attendancePath = Paths.get("src","Database", "Attendances");
        Path dirPath = attendancePath.resolve(classGrade).resolve(course);

        // Full path to the attendance file
        Path attendanceFilePath = dirPath.resolve(date + ".csv");
        return DatabaseFunctions.readFile(attendanceFilePath);
    }
    public static boolean writeNewGrades(Map<String, String> grades, String classGrade, String course, String activity) {
        Path gradesPath = Paths.get("src","Database", "Grades");
        Path dirPath = gradesPath.resolve(classGrade).resolve(course);

        // Full path to the attendance file
        Path gradesFilePath = dirPath.resolve(activity + ".csv");

        // Check if file already exists
        if (Files.exists(gradesFilePath)) {
            return false;
        }

        try {
            // Create the necessary directories (up-to-date folder)
            Files.createDirectories(dirPath);

            // Write to file
            try (BufferedWriter writer = Files.newBufferedWriter(gradesFilePath)) {
                writer.write("Roll no,Grades\n"); // Header
                for (Map.Entry<String, String> entry : grades.entrySet()) {
                    writer.write(entry.getKey() + "," + entry.getValue() + "\n");
                }
            }

            return true;

        } catch (IOException e) {
            return false;
        }
    }
    public static String readGrades(String classGrade, String course, String activity) {
        // Path to the directory where attendance file will be stored
        Path gradesPath = Paths.get("src","Database", "Grades");
        Path dirPath = gradesPath.resolve(classGrade).resolve(course);

        // Full path to the attendance file
        Path attendanceFilePath = dirPath.resolve(activity + ".csv");
        return DatabaseFunctions.readFile(attendanceFilePath);
    }
}