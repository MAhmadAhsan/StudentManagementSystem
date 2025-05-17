package MainClasses;

import Details.Credentials;

public interface StudentFunctions {
    boolean isCredentialsMatched(Credentials credentials);
    String viewStudentInfo(String classGrade, String rollNo);
    String viewGrades(String classGrade, String courseName, String activity);
    String viewStudentAttendance(String classGrade, String courseName, String date);
}