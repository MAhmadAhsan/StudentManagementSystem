package MainClasses;

import Details.Credentials;

public interface TeacherFunctions {
    boolean isCredentialsMatched(Credentials credentials);
    void uploadStudentAttendance();
    void uploadStudentGrade();
}