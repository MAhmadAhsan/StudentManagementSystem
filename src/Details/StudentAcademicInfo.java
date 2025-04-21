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
        if (rollNo == null || rollNo.trim().isEmpty()) {
            throw new IllegalArgumentException("RollNo should not be empty.");
        } else if (!rollNo.matches("[0-9]+")) {
            throw new IllegalArgumentException("RollNo should be a number");
        }
        this.rollNo = rollNo;
    }
    public void setClassGrade(String classGrade) {
        if (classGrade == null || classGrade.trim().isEmpty()) {
            throw new IllegalArgumentException("ClassGrade should not be empty.");
        }
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
        if(courses == null || courses.trim().isEmpty()) {
            throw new IllegalArgumentException("Courses should not be empty.");
        }
        this.courses = courses;
    }

    // toStirng
    @Override
    public String toString() {
        return "Student Academic Info: " + "\nClass: "+ getClassGrade() + "\nRollNo: "+ getRollNo() + "\nCourses: "+ getCourses();
    }
}
