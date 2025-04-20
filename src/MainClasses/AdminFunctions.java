package MainClasses;
import Details.Credentials;

public interface AdminFunctions {
    void register(Credentials credentials);
    boolean isCredentialsMatched(Credentials credentials);
    void addNewTeacher(Teacher teacher);
    void removeTeacher(Teacher teacher);
    boolean addNewStudent(Student student);
    boolean removeStudent(Student student);
    boolean makeClass(String className);
    boolean removeClass(String className);
    boolean isRegistered();
    String viewCurrentClasses();
    String viewStudentDetail(String classGrade, String rollNo);
    String viewStudentsInClass(String classGrade);
}