package MainClasses;

import Details.Credentials;

import java.util.Map;

public interface TeacherFunctions {
    boolean isCredentialsMatched(Credentials credentials);
    public boolean uploadStudentAttendance(Map<String, String> attendance, String classGrade, String courseName, String date);
    boolean uploadStudentGrade(Map<String, String> grade, String classGrade, String courseName, String activity);
    public String viewStudentsInClass(String classGrade);
    public String viewStudentInfo(String classGrade, String rollNo);
    public String viewAttendance(String classGrade, String courseName, String date);
    public String viewGrades(String classGrade, String courseName, String activity);
}