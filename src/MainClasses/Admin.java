package MainClasses;

import Details.ContactInfo;
import Details.Credentials;
import DataAcessLayer.*;
import Details.PersonalInfo;
import org.jetbrains.annotations.NotNull;

import static DataAcessLayer.StudentData.writeNewStudentDirectory;

public class Admin extends Person implements AdminFunctions {
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
    public boolean isCredentialsMatched(@NotNull Credentials credentials) throws NullPointerException {
        if (credentials == null) {
            throw new NullPointerException("credentials is null");
        }
        return credentials.getUsername().equals(AdminData.readAdminUsername()) &&
                credentials.getPassword().equals(AdminData.readAdminPassword());
    }
    public void addNewTeacher(Teacher teacher){
    }
    public void removeTeacher(Teacher teacher){
    }
    public boolean addNewStudent(Student student){
        return writeNewStudentDirectory(student.getStudentAcademicInfo().getRollNo(), student.getStudentAcademicInfo().getClassGrade());
    }
    public void removeStudent(Student student){

    }
    public boolean makeClass(String className){
        if(className == null || className.isEmpty()){
            throw new NullPointerException("You must specify a class name");
        }
        return StudentData.writeNewClass(className);
    }
    public void removeClass(String className){
        if(className == null || className.isEmpty()){
            throw new NullPointerException("You must specify a class name");
        }
        StudentData.deleteClass(className);
    }
    public boolean isRegistered(){
        return !AdminData.readAdminUsername().isEmpty() && !AdminData.readAdminPassword().isEmpty();
    }


}
