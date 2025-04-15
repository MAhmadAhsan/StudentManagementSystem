package MainClasses;

import Details.AcademicInfo;
import Details.ContactInfo;

import static DataAcessLayer.StudentData.writeNewStudentDirectory;

public class Student extends Person {
     private AcademicInfo academicInfo;
     // Constructors
     public Student(){}
     public Student(PersonalInfo personalInfo, AcademicInfo academicInfo, ContactInfo contactInfo) {
          super(personalInfo, contactInfo);
          setAcademicInfo(academicInfo);
     }

     // Getters
     public AcademicInfo getAcademicInfo() {
          if (academicInfo == null) {
               throw new NullPointerException("AcademicInfo has not been initialized.");
          }
          return academicInfo;
     }

     // Setters
     public void setAcademicInfo(AcademicInfo academicInfo) {

          this.academicInfo = academicInfo;
     }

     // to String
     @Override
     public String toString() {
          return super.toString() + "\n " + getAcademicInfo();
     }
}
