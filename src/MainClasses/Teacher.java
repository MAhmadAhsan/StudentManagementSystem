package MainClasses;

import Details.*;
import Details.TeacherAcademicInfo;

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
        return true; //written temprary
    }
    public void uploadStudentAttendance(){

    }
    public void uploadStudentGrade(){

    }
}
