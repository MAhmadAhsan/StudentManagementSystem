package MainClasses;

import Details.Credentials;
import Details.PersonalInfo;
import Details.StudentAcademicInfo;
import Details.ContactInfo;

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
          return true; //written temporary
     }
     public void viewInfo(){

     }
     public void viewGrades(){

     }
     public void viewAttendance(){

     }

}
