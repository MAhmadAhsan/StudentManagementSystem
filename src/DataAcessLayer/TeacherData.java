package DataAcessLayer;

import Database.DatabaseFunctions;
import MainClasses.Teacher;

import java.io.File;
import java.nio.file.*;

import static Database.DatabaseFunctions.*;

public class TeacherData {
    private static final Path teacherPath = Paths.get("src","Database","Teachers");
    public static boolean writeNewTeacher(Teacher teacher){
        String username = teacher.getCredentials().getUsername();
        Path teacherDirPath = teacherPath.resolve(username);
        File teacherDir = teacherDirPath.toFile();
        if (teacherDir.exists()) {
            return false;
        }else if (teacherDir.mkdirs()){
            Path teacherInfoPath = teacherDirPath.resolve("info.txt");
            Path teacherPasswordPath = teacherDirPath.resolve("password.txt");
            Path teacherUsernamePath = teacherDirPath.resolve("username.txt");
            return writeFile(teacher.toString(), teacherInfoPath) && writeFile(teacher.getCredentials().getPassword(), teacherPasswordPath) && writeFile(teacher.getCredentials().getUsername(), teacherUsernamePath);
        }else{
            return false;
        }
    }
    public static boolean deleteTeacher(String teacherUsername){
        // Resolves the path to the class grade directory
        Path teacherDirPath = teacherPath.resolve(teacherUsername);
        // Converts the Path object to a File object for checking existence
        File teacherDir = teacherDirPath.toFile();

        // Checks if the directory exists
        if (teacherDir.exists()) {
            try {
                // Attempts to delete the directory and its contents recursively
                return DatabaseFunctions.deleteFileRecursively(teacherDirPath);
            } catch (Exception e) {
                // Returns false if an error occurs during the deletion process
                return false;
            }
        } else {
            // Returns false if the directory does not exist
            return false;
        }
    }
    public static String getAllTeachers(){
        Path path = teacherPath;
        File file = path.toFile();
        if(!file.exists() || !file.isDirectory()){
            return null;
        }
        return DatabaseFunctions.allChildFile(path);
    }
    public static String readTeacherDetails(String teacherUsername){
        Path TeacherInfoPath = teacherPath.resolve(teacherUsername,"info.txt");
        File TeacherInfoFile = TeacherInfoPath.toFile();

        if (!TeacherInfoFile.exists()) {
            return null;
        }
        return DatabaseFunctions.readFile(TeacherInfoPath);
    }
    public static boolean isTeacherExists(String teacherUsername){
        Path TeacherInfoPath = teacherPath.resolve(teacherUsername);
        File TeacherInfoFile = TeacherInfoPath.toFile();
        return TeacherInfoFile.exists();
    }
    public static String readTeacherPassword(String teacherUsername){
        Path TeacherPasswordPath = teacherPath.resolve(teacherUsername,"password.txt");
        File TeacherInfoFile = TeacherPasswordPath.toFile();
        if (!TeacherInfoFile.exists()) {
            return null;
        }
        return DatabaseFunctions.readFile(TeacherPasswordPath);
    }
}
