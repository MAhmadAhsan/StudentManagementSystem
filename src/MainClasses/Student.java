package MainClasses;

import Details.Credentials;
import Details.PersonalInfo;
import Details.StudentAcademicInfo;
import Details.ContactInfo;

import java.security.SecureRandom;

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
          return true;
     }
     public void viewInfo(){

     }
     public void viewGrades(){

     }
     public void viewAttendance(){

     }

     /**
      * Generates student credentials using the student's academic information.
      * <p>
      * The username is created by removing all spaces from the class grade and appending
      * a dash followed by the student's roll number (e.g., "10A-123").
      * The password is a securely generated 8-digit random number.
      *
      * @return a {@link Credentials} object containing the generated username and password
      */
     public Credentials generateStudentCredentials() {
          Credentials credentials = new Credentials();
          // Generate username: classGrade with no spaces + "-" + roll number
          credentials.setUsername(this.getStudentAcademicInfo().getClassGrade().replaceAll("\\s+", "") + "0-0" + this.getStudentAcademicInfo().getRollNo());
          // Generate a secure 8-digit random password
          SecureRandom secureRandom = new SecureRandom();
          int random8Digit = 100000000 + secureRandom.nextInt(900000000);
          // Convert int to String
          credentials.setPassword(String.valueOf(random8Digit));
          return credentials;
     }

     /**
      * Parses the given username to extract the classGrade and roll number.
      * <p>
      * The expected format of the username is "ClassGrade-RollNumber", e.g., "10A-123".
      * It splits the string at the last dash ('-') character to separate the two components.
      *
      * @param username the username string to parse
      * @return a String array where index 0 contains the classGrade, and index 1 contains the roll number;
      *         returns null if the format is invalid (i.e., dash not found)
      */
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
}
