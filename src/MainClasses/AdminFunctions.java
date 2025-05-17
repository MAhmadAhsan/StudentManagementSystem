package MainClasses;
import Details.Credentials;

public interface AdminFunctions {
    void register(Credentials credentials);
    boolean isCredentialsMatched(Credentials credentials);
    boolean addNewTeacher(Teacher teacher);
    boolean removeTeacher(String username);
    boolean addNewStudent(Student student);
    boolean removeStudent(Student student);
    boolean makeClass(String className);
    boolean removeClass(String className);
    boolean isRegistered();
    String viewCurrentClasses();
    String viewStudentInfo(String classGrade, String rollNo);
    String viewStudentsInClass(String classGrade);
    String viewTeacherInfo(String username);
    String viewAllTeachers();
}