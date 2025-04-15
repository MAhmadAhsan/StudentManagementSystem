package MainClasses;

import Details.Credentials;

public interface AdminFunctions {
    void register(Credentials credentials);
    boolean isCredentialsMatched(Credentials credentials);
    void addNewTeacher(Teacher teacher);
    void removeTeacher(Teacher teacher);
    boolean addNewStudent(Student student);
    void removeStudent(Student student);
    boolean makeClass(String className);
    void removeClass(String className);
}
