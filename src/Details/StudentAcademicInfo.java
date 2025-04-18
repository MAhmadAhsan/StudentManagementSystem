package Details;

public class StudentAcademicInfo {
    private String rollNo;
    private String classGrade;
    private String courses;

    // Constructors
    public StudentAcademicInfo() {}
    public StudentAcademicInfo(String rollNo, String classGrade, String courses) {
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
        return "Student Academic Info:" + "\nClass :"+ getClassGrade() + "\nRollNo :"+ getRollNo() + "\nCourses :"+ getCourses();
    }
}
