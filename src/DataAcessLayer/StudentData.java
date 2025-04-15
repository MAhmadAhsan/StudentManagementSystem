package DataAcessLayer;
import MainClasses.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StudentData {
    public static boolean writeNewClass(String classGrade) {
        File directory = new File("src/Database/SchoolClasses/" + classGrade);
        if (directory.exists()) {
            return false;
        } else {
            return directory.mkdirs();
        }
    }

    public static boolean deleteClass(String classGrade) {
        File directory = new File("src/Database/SchoolClasses/" + classGrade);
        if (directory.exists()) {
            return directory.delete();
        } else {
            return false;
        }
    }

    public static boolean writeNewStudentDirectory(String rollNo, String classGrade) {

        if (rollNo == null || classGrade == null) {
            throw new IllegalStateException("Student has not been initialized.");
        }
        Path classGradeDirectoryPath = Paths.get("src", "Database", "SchoolClasses", classGrade);
        Path rollNoDirectoryPath = classGradeDirectoryPath.resolve(rollNo);

        File classGradeDirectory = classGradeDirectoryPath.toFile();
        File rollNoDirectory = rollNoDirectoryPath.toFile();

        if(isThisStudentExists(rollNo, classGrade)) {
            return false;
        }else{
            return rollNoDirectory.mkdirs();
        }
    }
    public static boolean deleteStudent(String rollNo, String classGrade) {
        if (rollNo == null || classGrade == null) {
            throw new IllegalStateException("Student has not been initialized.");
        }
        Path classGradeDirectoryPath = Paths.get("src", "Database", "SchoolClasses", classGrade);
        Path rollNoDirectoryPath = classGradeDirectoryPath.resolve(rollNo);

        File classGradeDirectory = classGradeDirectoryPath.toFile();
        File rollNoDirectory = rollNoDirectoryPath.toFile();

        if(isThisStudentExists(rollNo, classGrade)) {
            return rollNoDirectory.delete();
        }else{
            return false;
        }
    }

//    public static boolean isChildPresentInParent(File parentDirectory,  File childDirectory) {
//        if (parentDirectory.exists()) {
//            return childDirectory.exists();
//        }else{
//            return false;
//        }
//    }

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
}




