package MainClasses;

import Details.Credentials;

public interface StudentFunctions {
    boolean isCredentialsMatched(Credentials credentials);
    void viewInfo();
    void viewGrades();
    void viewAttendance();
}
