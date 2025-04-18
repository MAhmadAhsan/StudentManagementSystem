package MainClasses;

import Details.ContactInfo;
import Details.Credentials;
import DataAcessLayer.*;
import Details.PersonalInfo;
import org.jetbrains.annotations.NotNull;

import static DataAcessLayer.StudentData.*;

public class Admin extends User implements AdminFunctions {
    private boolean isLoggedIn = false;
    // Constructors
    public Admin(){}
    public Admin(PersonalInfo personalInfo, ContactInfo contactInfo, Credentials credentials) {
        super(personalInfo, contactInfo, credentials);
    }

    // Getters
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    //Setters
    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    // toString
    @Override
    public String toString() {
        return super.toString();
    }


    public void register(@NotNull Credentials credentials) throws NullPointerException {
        if(credentials == null){
            throw new NullPointerException();
        }
        AdminData.writeAdminUsername(credentials.getUsername());
        AdminData.writeAdminPassword(credentials.getPassword());
    }
    public boolean isCredentialsMatched(@NotNull Credentials credentials) {
        String storedUsername = AdminData.readAdminUsername();
        String storedPassword = AdminData.readAdminPassword();

        return storedUsername != null &&
                storedPassword != null &&
                credentials.getUsername().equals(storedUsername) &&
                credentials.getPassword().equals(storedPassword);
    }

    public void addNewTeacher(Teacher teacher){
    }
    public void removeTeacher(Teacher teacher){
    }
    public boolean addNewStudent(Student student){
        return writeNewStudentDirectory(student);
    }
    public boolean removeStudent(Student student){
        return deleteStudentDirectory(student.getStudentAcademicInfo().getRollNo(), student.getStudentAcademicInfo().getClassGrade());
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
        return getAllClasses();
    }
    public String viewStudentDetail(String classGrade, String rollNo){
        return readStudentDetails(classGrade,rollNo);
    }
}
