package MainClasses;
import DataAcessLayer.*;
import Details.*;

import static DataAcessLayer.StudentData.*;
import static DataAcessLayer.TeacherData.*;

public class Admin extends User implements AdminFunctions {
    // Constructors
    public Admin(){}

    public Admin(PersonalInfo personalInfo, ContactInfo contactInfo, Credentials credentials) {
        super(personalInfo, contactInfo, credentials);
    }

    // toString
    @Override
    public String toString() {
        return super.toString();
    }

    public void register(Credentials credentials) throws NullPointerException {
        if(credentials == null){
            throw new NullPointerException();
        }
        AdminData.writeAdminUsername(credentials.getUsername());
        AdminData.writeAdminPassword(credentials.getPassword());
    }

    public boolean isCredentialsMatched(Credentials credentials) {
        String storedUsername = AdminData.readAdminUsername();
        String storedPassword = AdminData.readAdminPassword();

        return storedUsername != null &&
                storedPassword != null &&
                credentials.getUsername().equals(storedUsername) &&
                credentials.getPassword().equals(storedPassword);
    }

    public boolean addNewTeacher(Teacher teacher){
        return writeNewTeacher(teacher);
    }

    public boolean removeTeacher(String username){
        return deleteTeacher(username);
    }

    public String viewTeacherInfo(String username){
        return readTeacherDetails(username);
    }

    public boolean addNewStudent(Student student){
        return writeNewStudent(student);
    }
    public boolean removeStudent(Student student){
        return deleteStudentDir(student.getStudentAcademicInfo().getClassGrade(), student.getStudentAcademicInfo().getRollNo());
    }
    public boolean makeClass(String classGrade){
        if(classGrade == null || classGrade.isEmpty()){
            throw new NullPointerException("You must specify a class name");
        }
        return StudentData.writeNewClassGrade(classGrade);
    }
    public boolean removeClass(String classGrade){
        if(classGrade == null || classGrade.isEmpty()){
            throw new NullPointerException("You must specify a class name");
        }
        return StudentData.deleteClassGrade(classGrade);
    }
    public boolean isRegistered() {
        String username = AdminData.readAdminUsername();
        String password = AdminData.readAdminPassword();
        return username != null && !username.isBlank() &&
                password != null && !password.isBlank();
    }
    public String viewCurrentClasses(){
        return readAllClasses();
    }
    public String viewStudentInfo(String classGrade, String rollNo){
        return readStudentDetails(classGrade,rollNo);
    }
    public String viewStudentsInClass(String classGrade){
        return readStudentsInClass(classGrade);
    }
    public String viewAllTeachers(){
        return getAllTeachers();
    }
}