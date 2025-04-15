package MainClasses;

import Details.Credentials;
import Details.StudentAcademicInfo;
import Details.ContactInfo;

public class Student extends Person {
     private StudentAcademicInfo studentAcademicInfo;
     // Constructors
     public Student(){}
     public Student(PersonalInfo personalInfo, ContactInfo contactInfo, Credentials credentials, StudentAcademicInfo studentAcademicInfo) {
          super(personalInfo, contactInfo, credentials);
          setStudentAcademicInfo(studentAcademicInfo);
     }

     // Getters
     public StudentAcademicInfo getStudentAcademicInfo() {
          if (studentAcademicInfo == null) {
               throw new NullPointerException("StudentAcademicInfo has not been initialized.");
          }
          return studentAcademicInfo;
     }

     // Setters
     public void setStudentAcademicInfo(StudentAcademicInfo studentAcademicInfo) {

          this.studentAcademicInfo = studentAcademicInfo;
     }

     // to String
     @Override
     public String toString() {
          return super.toString() + "\n " + getStudentAcademicInfo();
     }
}
