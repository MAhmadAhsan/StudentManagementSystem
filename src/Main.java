import java.util.InputMismatchException;
import java.util.Scanner;
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
                                loggedInAdmin(admin);
                                keepRunning = false;
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
                                loggedInTeacher(teacher);
                                keepRunning = false;
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
                                System.err.println("Invalid Student credentials. Try again.");
                            }
                        }
                    }

                    // Exit app
                    case 0 -> {
                        exitApp();
                        keepRunning = false;
                    }

                    default -> {
                        System.err.println("Invalid choice. Please try again.");
                    }
                }
            }catch (Exception e){
                e.getMessage();
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
                        System.out.println("All available classes...");
                        System.out.println(admin.viewCurrentClasses());
                    }

                    // Manage Students
                    // Add a new student
                    case 4 -> {
                        System.out.println("Enter student details to add:");
                        Student student = ConsoleUtils.studentInfoForm();
                        Credentials credentials = student.generateStudentCredentials();
                        System.out.println(credentials);
                        student.setCredentials(credentials);
                        if (admin.addNewStudent(student)) {
                            System.out.println("Student added: " + student.getCredentials().getUsername());
                            System.out.println("Student password: " + student.getCredentials().getPassword());
                        } else {
                            System.err.println("Student not added.");
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
                        Student student = new Student();
                        System.out.println("Enter student Class and Roll no to view:");
                        student = ConsoleUtils.classGradeAndRollNoForm();
                        System.out.println(admin.viewStudentDetail(student.getStudentAcademicInfo().getClassGrade(),student.getStudentAcademicInfo().getRollNo()));
                    }

                    // Recover student credentials
                    case 7 -> {
                        System.out.println("Recover student Credentials coming soon");
                    }

                    // view All Students in a class
                    case 8 ->{
                        System.out.println("All Students in a Class");
                        System.out.println(admin.viewStudentsInClass(ConsoleUtils.classGradeField()));
                    }

                    // Manage Teachers
                    // Add a new teacher
                    case 9 -> {
                        System.out.println("Enter teacher details to add:");
                        Teacher teacher = ConsoleUtils.teacherInfo();
                        Credentials credentials = teacher.generateTeacherCredentials();
                        System.out.println(credentials);
                        teacher.setCredentials(credentials);
                        if (admin.addNewTeacher(teacher)) {
                            System.out.println("Teacher username: " + teacher.getCredentials().getUsername());
                            System.out.println("Teacher password: " + teacher.getCredentials().getPassword());
                        } else {
                            System.err.println("Teacher not added.");
                        }
                    }

                    // Remove a teacher
                    case 10 -> {
                        System.out.println("Enter teacher username:");
                        if (admin.removeTeacher(scanner.nextLine())){
                            System.out.println("Teacher removed.");
                        } else {
                            System.err.println("Teacher not found or not removed.");
                        }
                    }

                    // View teacher Info
                    case 11 -> {
                        Student student = new Student();
                        System.out.println("Enter teacher username:");
                        System.out.println(admin.viewTeacherDetail(scanner.nextLine()));
                    }

                    // Recover teacher Credentials
                    case 12 -> {
                        System.out.println("Recover teacher Credentials coming soon");
                    }

                    // View all teachers
                    case 13 -> {
                        System.out.println("All teachers");
                        System.out.println(admin.viewAllTeachers());
                    }

                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                e.printStackTrace();
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter valid values.");
                scanner.nextLine(); // clear buffer
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
                        System.out.println("Coming soon...");
                    }

                    // View All Students in a class
                    case 2 -> {
                        System.out.println("Coming soon...");
                    }

                    // View all created classes
                    case 3 -> {
                        System.out.println("Coming soon...");
                    }

                    // Upload class Attendance
                    case 4 -> {
                        System.out.println("Coming soon...");
                    }

                    // View class Attendance
                    case 5 -> {
                        System.out.println("Coming soon...");
                    }

                    // Upload class Grades
                    case 6 -> {}


                    // View class Grades
                    case 7 -> {
                        System.out.println("Coming soon...");
                    }

                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("Select again");
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter valid values.");
                scanner.nextLine(); // clear buffer
            }
        }
    }
    public static void loggedInStudent(Student student){}
    public static void exitApp(){
        System.out.println("Exited Successfully...");
        System.exit(0);
    }
}