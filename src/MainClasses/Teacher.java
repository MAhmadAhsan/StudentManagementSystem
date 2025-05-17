package MainClasses;

import DataAcessLayer.StudentData;
import Database.DatabaseFunctions;
import Details.*;
import Details.TeacherAcademicInfo;
import java.security.SecureRandom;
import java.util.Map;

import static DataAcessLayer.StudentData.*;
import static DataAcessLayer.TeacherData.isTeacherExists;
import static DataAcessLayer.TeacherData.readTeacherPassword;

public class Teacher extends User implements TeacherFunctions {
    TeacherAcademicInfo teacherAcademicInfo;

    // Constructor
    public Teacher(){}
    public Teacher(PersonalInfo personalInfo, ContactInfo contactInfo, Credentials Credentials, TeacherAcademicInfo teacherAcademicInfo) {
        super(personalInfo, contactInfo, Credentials);
        setTeacherAcademicInfo(teacherAcademicInfo);
    }

    // Getters
    public TeacherAcademicInfo getTeacherAcademicInfo() {
        return teacherAcademicInfo;
    }

    // Setters
    public void setTeacherAcademicInfo(TeacherAcademicInfo teacherAcademicInfo) {
        this.teacherAcademicInfo = teacherAcademicInfo;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + "\n " + getTeacherAcademicInfo();
    }
    public boolean isCredentialsMatched(Credentials credentials){
        if(isTeacherExists(credentials.getUsername())){
            return credentials.getPassword().equals(readTeacherPassword(credentials.getUsername()));
        }else{
            return false;
        }
    }
    public boolean uploadStudentAttendance(Map<String, String> attendance, String classGrade, String courseName, String date) {
        return writeNewAttendance(attendance, classGrade, courseName, date);
    }
    public String viewStudentAttendance(String classGrade, String courseName, String date) {
        return StudentData.readAttendance(classGrade, courseName, date);
    }

    public boolean uploadStudentGrade(Map<String, String> grade, String classGrade, String courseName, String activity){
        return writeNewGrades(grade, classGrade, courseName, activity);
    }
    public Credentials generateTeacherCredentials() {
        Credentials credentials = new Credentials();
        SecureRandom secureRandom = new SecureRandom();
        credentials.setUsername(this.getPersonalInfo().getName().replaceAll("\\s+", "").toLowerCase() + (10000 + secureRandom.nextInt(90000)));
        int random8Digit = 100000000 + secureRandom.nextInt(900000000);
        // Convert int to String
        credentials.setPassword(String.valueOf(random8Digit));
        return credentials;
    }
    public String viewStudentsInClass(String classGrade){
        return readStudentsInClass(classGrade);
    }
    public String viewStudentInfo(String classGrade, String rollNo){
        return readStudentDetails(classGrade,rollNo);
    }
    public String viewAttendance(String classGrade, String courseName, String date){
        return viewStudentAttendance(classGrade, courseName, date);
    }
    public String viewGrades(String classGrade, String courseName, String activity){
        return readGrades(classGrade, courseName, activity);
    }
    public String viewCurrentClasses(){
        return readAllClasses();
    }

}
