package Details;

public class TeacherAcademicInfo {
    private String coursesTaught;

    // Constructors
    public TeacherAcademicInfo() {}
    public TeacherAcademicInfo(String rollNo, String classGrade, String courses) {
        this.setRollNo(rollNo);
        this.setClassGrade(classGrade);
        this.setCourses(courses);
    }

    // Getters
    public String getCoursesTaught() {
        return coursesTaught;
    }

    // Setters
    public void setCoursesTaught(String coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    // toStirng
    @Override
    public String toString() {
        return "Class :"+ getClassGrade() + "\nRollNo :"+ getRollNo() + "\nCourses :"+ getCourses();
    }
}
