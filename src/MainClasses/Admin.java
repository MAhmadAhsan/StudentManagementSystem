package MainClasses;

import Details.ContactInfo;
import Details.Credentials;
import DataAcessLayer.*;
import org.jetbrains.annotations.NotNull;

import static DataAcessLayer.StudentData.writeNewStudentDirectory;

public class Admin extends Person implements AdminFunctions {
    private Credentials credentials;
    private boolean isLoggedIn = false;
    // Constructors
    public Admin(){}
    public Admin(PersonalInfo personalInfo, ContactInfo contactInfo, Credentials Credentials) {
        super(personalInfo, contactInfo);
        setCredentials(Credentials);
    }

    // Getters
    public Credentials getCredentials() {
            return credentials;
    }
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    //Setters
    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
    public void setLoggedin(boolean loggedin) {
        isLoggedIn = loggedin;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + "\n" + getCredentials();
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
        return writeNewStudentDirectory(student.getAcademicInfo().getRollNo(), student.getAcademicInfo().getClassGrade());
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
