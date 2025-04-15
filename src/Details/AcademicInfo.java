package Details;

import java.util.List;
public class AcademicInfo {
    private String rollNo;
    private String classGrade;
    private String courses;

    // Constructors
    public AcademicInfo() {}
    public AcademicInfo(String rollNo, String classGrade, String courses) {
        this.setRollNo(rollNo);
        this.setClassGrade(classGrade);
        this.setCourses(courses);
    }

    // Getters
    public String getRollNo() {
        return rollNo;
    }
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }
    public String getClassGrade() {
        return classGrade;
    }
    // Setters
    public String getCourses() {
        return courses;
    }
    public void setCourses(String courses) {
        this.courses = courses;
    }

    // toStirng
    @Override
    public String toString() {
        return "Class :"+ getClassGrade() + "\nRollNo :"+ getRollNo() + "\nCourses :"+ getCourses();
    }
}
