package MainClasses;
import DataAcessLayer.AdminData;
import Details.*;
import java.security.SecureRandom;
import DataAcessLayer.StudentData;

import static DataAcessLayer.StudentData.*;

public class Student extends User implements StudentFunctions {
     private StudentAcademicInfo studentAcademicInfo;
     // Constructors
     public Student(){}
     public Student(PersonalInfo personalInfo, ContactInfo contactInfo, StudentAcademicInfo studentAcademicInfo, Credentials credentials) {
          super(personalInfo, contactInfo, credentials);
          setStudentAcademicInfo(studentAcademicInfo);
     }
     // Getters
     public StudentAcademicInfo getStudentAcademicInfo() {
          return studentAcademicInfo;
     }
     // Setters
     public void setStudentAcademicInfo(StudentAcademicInfo studentAcademicInfo) {
          this.studentAcademicInfo = studentAcademicInfo;
     }
     // to String
     @Override
     public String toString() {
          return super.toString() + "\n" + getStudentAcademicInfo();
     }
     public boolean isCredentialsMatched(Credentials credentials){
          String[] classGradeAndRollNo = parseUsername(credentials.getUsername());
          String storedUsername = StudentData.readStudentUsername(classGradeAndRollNo[0],classGradeAndRollNo[1]);
          String storedPassword = StudentData.readStudentPassword(classGradeAndRollNo[0],classGradeAndRollNo[1]);

          return credentials.getUsername().equals(storedUsername) &&
                  credentials.getPassword().equals(storedPassword);
     }
     public void viewInfo(){

     }
     public String viewGrades(String classGrade, String courseName, String activity){
          return readGrades(classGrade, courseName, activity);
     }
     public Credentials generateStudentCredentials() {
          Credentials credentials = new Credentials();
          credentials.setUsername(this.getStudentAcademicInfo().getClassGrade().replaceAll("\\s+", "").toLowerCase() + "-" + this.getStudentAcademicInfo().getRollNo());
          SecureRandom secureRandom = new SecureRandom();
          int random8Digit = 10000000 + secureRandom.nextInt(90000000);
          credentials.setPassword(String.valueOf(random8Digit));
          return credentials;
     }
     public String[] parseUsername(String username) {
          int lastDashIndex = username.lastIndexOf('-');

          if (lastDashIndex == -1) {
               return null; // Invalid format, dash not found
          }

          String[] result = new String[2];
          result[0] = username.substring(0, lastDashIndex);      // Class and Grade
          result[1] = username.substring(lastDashIndex + 1);     // Roll Number

          return result;
     }
     public String viewStudentInfo(String classGrade, String rollNo){
          return readStudentDetails(classGrade,rollNo);
     }
     public String viewStudentAttendance(String classGrade, String courseName, String date) {
          return StudentData.readAttendance(classGrade, courseName, date);
     }
}
