package Details;

public class TeacherAcademicInfo {
    private String coursesTaught;
    private String Qualification;

    // Constructors
    public TeacherAcademicInfo() {}
    public TeacherAcademicInfo(String CoursesTaught, String Qualification) {
        this.setCoursesTaught(CoursesTaught);
        this.setQualification(Qualification);
    }

    // Getters
    public String getCoursesTaught() {
        return coursesTaught;
    }
    public String getQualification() {
        return Qualification;
    }

    // Setters
    public void setCoursesTaught(String coursesTaught) {
        if (coursesTaught== null || coursesTaught.isEmpty()){
            throw new IllegalArgumentException("Courses Taught cannot be empty");
        }
        this.coursesTaught = coursesTaught;
    }
    public void setQualification(String qualification) {
        if (qualification == null || qualification.isEmpty()){
            throw new IllegalArgumentException("Qualification cannot be empty");
        }
        this.Qualification = qualification;
    }


    // toStirng
    @Override
    public String toString() {
        return "Teacher Academic Info:" + "\nCourses Taught: "+ getCoursesTaught() + "\nQualification: "+  getQualification();
    }
}
