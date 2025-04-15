package MainClasses;

import Details.Credentials;

public interface TeacherFunctions {
    public boolean login(Credentials credentials);
    public void uploadStudentAttendance();
    public void uploadStudentGrade();
}
