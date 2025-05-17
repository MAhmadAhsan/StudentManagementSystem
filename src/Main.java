import java.util.*;

import Details.Credentials;
import MainClasses.*;
import PresentationLayer.*;

import static PresentationLayer.ConsoleUtils.*;

public class Main {
    public static void main(String[] args) {
        ConsoleUtils.title();
        registerAdmin();
        loginUser();
    }
    public static void registerAdmin() {
        Admin admin = new Admin();

        while (!admin.isRegistered()) {
            try {
                Credentials credentials = ConsoleUtils.credentialsForm("Register", "Admin");
                admin.register(credentials);
                System.out.println("Admin registered");
            } catch (NullPointerException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
    public static void loginUser() {
        boolean keepRunning = true;
        while (keepRunning) {
            try{
                switch (userLoginTypeForm()) {
                    // Login as Admin
                    case 1 -> {
                        while(true){
                            Admin admin = new Admin();
                            Credentials credentials = credentialsForm("Login", "Admin");
                            if(credentials == null){
                                break;
                            }
                            if (admin.isCredentialsMatched(credentials)) {
                                admin.setCredentials(credentials);
                                System.out.println("Admin logged in");
                                keepRunning = false;
                                loggedInAdmin(admin);
                                break;
                            } else {
                                System.out.println("Invalid Admin credentials. Try again.");
                            }
                        }
                    }

                    // Login as Teacher
                    case 2 -> {
                        while(true){
                            Teacher teacher = new Teacher();
                            Credentials credentials = credentialsForm("Login", "Teacher");

                            if(credentials == null){
                                break;
                            }

                            if (teacher.isCredentialsMatched(credentials)) {
                                teacher.setCredentials(credentials);
                                System.out.println("Teacher logged in");
                                keepRunning = false;
                                loggedInTeacher(teacher);
                            } else {
                                System.out.println("Invalid Teacher credentials. Try again.");
                            }
                        }
                    }

                    // Login as Student
                    case 3 -> {
                        while(true){
                            Student student = new Student();
                            Credentials credentials = credentialsForm("Login", "Student");

                            if(credentials == null){
                                break;
                            }
                            if (student.isCredentialsMatched(credentials)) {
                                student.setCredentials(credentials);

                                System.out.println("Student logged in");
                                loggedInStudent(student);
                                keepRunning = false;
                            } else {
                                System.out.println("Invalid Student credentials. Try again.");
                            }
                        }
                    }

                    // Exit app
                    case 0 -> {
                        exitApp();
                        keepRunning = false;
                    }

                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }
    public static void loggedInAdmin(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        boolean stayLoggedIn = true;
        while (stayLoggedIn) {
            try {
                switch (adminFunctionSelector()) {
                    // Logout
                    case 0 -> {
                        stayLoggedIn = false;
                        loginUser();
                    }

                    // Classes Manager
                    // Make a new class
                    case 1 -> {
                        if (admin.makeClass(ConsoleUtils.classGradeField())) {
                            System.out.println("Class added.");
                        } else {
                            System.out.println("Class already exists.");
                        }
                    }

                    // Remove a class
                    case 2 -> {
                        System.out.println("Enter class name to remove:");
                        if (admin.removeClass(scanner.nextLine())) {
                            System.out.println("Class removed.");
                        } else {
                            System.out.println("Class does not exist.");
                        }
                    }

                    // View all created classes
                    case 3 -> {
                        System.out.println("All Registered classes...");
                        System.out.println(admin.viewCurrentClasses());
                    }

                    // Manage Students
                    // Add a new student
                    case 4 -> {
                        System.out.println("Enter student details to add:");
                        Student student = ConsoleUtils.studentInfoForm();
                        Credentials credentials = student.generateStudentCredentials();
                        student.setCredentials(credentials);
                        if (admin.addNewStudent(student)) {
                            System.out.println("Student Credentials Generated");
                            System.out.println("Student Username: " + student.getCredentials().getUsername());
                            System.out.println("Student password: " + student.getCredentials().getPassword());
                        } else {
                            System.out.println("Student not added.");
                        }
                    }

                    // Remove a current Student
                    case 5 -> {
                        System.out.println("Enter student Class and Roll no to remove:");
                        if (admin.removeStudent(ConsoleUtils.classGradeAndRollNoForm())) {
                            System.out.println("Student removed.");
                        } else {
                            System.err.println("Student not found or not removed.");
                        }
                    }

                    //View Student Info
                    case 6 -> {
                        System.out.println("Enter student Class and Roll no to view:");
                        Student student = ConsoleUtils.classGradeAndRollNoForm();
                        System.out.println("Student Info:");
                        System.out.println(admin.viewStudentInfo(student.getStudentAcademicInfo().getClassGrade(),student.getStudentAcademicInfo().getRollNo()));
                    }

                    // view All Students in a class
                    case 7 ->{
                        System.out.println("All Students in a Class");
                        System.out.println(admin.viewStudentsInClass(ConsoleUtils.classGradeField()));
                    }

                    // Manage Teachers
                    // Add a new teacher
                    case 8 -> {
                        System.out.println("Enter teacher details to add:");
                        Teacher teacher = ConsoleUtils.teacherInfo();
                        Credentials credentials = teacher.generateTeacherCredentials();
                        System.out.println(credentials);
                        teacher.setCredentials(credentials);
                        if (admin.addNewTeacher(teacher)) {
                            System.out.println("Teacher username: " + teacher.getCredentials().getUsername());
                            System.out.println("Teacher password: " + teacher.getCredentials().getPassword());
                        } else {
                            System.out.println("Teacher not added.");
                        }
                    }

                    // Remove a teacher
                    case 9 -> {
                        System.out.println("Enter teacher username:");
                        if (admin.removeTeacher(scanner.nextLine())){
                            System.out.println("Teacher removed.");
                        } else {
                            System.out.println("Teacher not found or not removed.");
                        }
                    }

                    // View teacher Info
                    case 10 -> {
                        Student student = new Student();
                        System.out.println("Enter teacher username:");
                        System.out.println(admin.viewTeacherInfo(scanner.nextLine()));
                    }

                    // View all teachers
                    case 11 -> {
                        System.out.println("All teachers");
                        System.out.println(admin.viewAllTeachers());
                    }

                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("Select again");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid values.");
            }
        }
    }
    public static void loggedInTeacher(Teacher teacher){
        Scanner scanner = new Scanner(System.in);
        boolean stayLoggedIn = true;
        while (stayLoggedIn) {
            try{
                int choice = ConsoleUtils.teacherFunctionSelector();
                switch (choice) {
                    // Logout
                    case 0 -> {
                        stayLoggedIn = false;
                        loginUser();
                    }

                    // View Student Info
                    case 1 -> {
                        System.out.println("Enter student Class and Roll no to view:");
                        Student student = ConsoleUtils.classGradeAndRollNoForm();
                        System.out.println("Students Info:");
                        System.out.println(teacher.viewStudentInfo(student.getStudentAcademicInfo().getClassGrade(),student.getStudentAcademicInfo().getRollNo()));
                    }

                    // View All Students in a class
                    case 2 -> {
                        System.out.println("All Students in a Class");
                        System.out.println(teacher.viewStudentsInClass(ConsoleUtils.classGradeField()));
                    }

                    // View all created classes
                    case 3 -> {
                        System.out.println("All Registered classes...");
                        System.out.println(teacher.viewCurrentClasses());
                    }

                    // Upload class Attendance
                    case 4 -> {
                        String classGrade = classGradeField();
                        String courseName = courseNameField();
                        String date = dateField();
                        String studentsInClass = teacher.viewStudentsInClass(classGrade);
                        Map<String, String> attendance = ConsoleUtils.uploadAttendanceForm(studentsInClass, classGrade, courseName, date);
                        if(teacher.uploadStudentAttendance(attendance, classGrade, courseName, date)){
                            System.out.println("Attendance uploaded successfully.");
                        }else{
                            System.out.println("Error occurred during attendance upload.");
                        }
                    }

                    // View class Attendance
                    case 5 -> {
                        String classGrade = classGradeField();
                        String courseName = courseNameField();
                        String date = dateField();
                        System.out.println(teacher.viewAttendance(classGrade, courseName, date));
                    }

                    // Upload class Grades
                    case 6 -> {
                        String classGrade = classGradeField();
                        String courseName = courseNameField();
                        String activity = classActivityField();
                        String studentsInClass = teacher.viewStudentsInClass(classGrade);
                        Map<String, String> grades = ConsoleUtils.uploadGradesForm(studentsInClass, classGrade, courseName, activity);
                        if(teacher.uploadStudentGrade(grades, classGrade, courseName, activity)){
                            System.out.println("Grades uploaded successfully.");
                        }else{
                            System.out.println("Error occurred during Grades upload.");
                        }
                    }

                    // View class Grades
                    case 7 -> {
                        String classGrade = classGradeField();
                        String courseName = courseNameField();
                        String activity = classActivityField();
                        System.out.println(teacher.viewGrades(classGrade, courseName, activity));
                    }

                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("Select again");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid values.");
                scanner.nextLine(); // clear buffer
            }
        }
    }
    public static void loggedInStudent(Student student){
        Scanner scanner = new Scanner(System.in);
        boolean stayLoggedIn = true;
        while (stayLoggedIn) {
            try {
                switch (studentFunctionSelector()) {
                    // Logout
                    case 0 -> {
                        stayLoggedIn = false;
                        loginUser();
                    }

                    // View Info
                    case 1 -> {
                        System.out.println("Your Info");
                        String[] classGradeAndRollNo = student.parseUsername(student.getCredentials().getUsername());
                        System.out.println(student.viewStudentInfo(classGradeAndRollNo[0],classGradeAndRollNo[1]));
                    }

                    // View Attendance
                    case 2 -> {
                        System.out.println("Attendance of your class");
                        String courseName = courseNameField();
                        String date = dateField();
                        String[] classGradeAndRollNo = student.parseUsername(student.getCredentials().getUsername());
                        System.out.println(student.viewStudentAttendance(classGradeAndRollNo[0], courseName, date));
                    }

                    // View Grades
                    case 3 -> {
                        System.out.println("Grades of your class");
                        String courseName = courseNameField();
                        String activity = classActivityField();
                        String[] classGradeAndRollNo = student.parseUsername(student.getCredentials().getUsername());
                        System.out.println(student.viewGrades(classGradeAndRollNo[0], courseName, activity));
                    }

                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("Select again an unknown error occurred.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid values.");
                scanner.nextLine(); // clear buffer
            }
        }
    }

    public static void exitApp(){
        System.out.println("Exited Successfully...");
        System.exit(0);
    }
}