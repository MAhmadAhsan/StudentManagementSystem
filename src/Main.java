import java.nio.channels.ScatteringByteChannel;
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
        // Register Admin

        if(!admin.isRegistered()) {
            while(!admin.isRegistered()){
                try{
                    admin = ConsoleUtils.registrationForm(admin);
                } catch (Exception e){
                    e.getMessage();
                }
            }
        }
    }
    public static void loginUser() {
        boolean keepRunning = true;
        while (keepRunning) {
            try{
                switch (userLoginTypeForm()) {
                    case 1: {
                        Admin admin = new Admin();
                        Credentials credentials = credentialsForm("Login", "Admin");

                        if (admin.isCredentialsMatched(credentials)) {
                            admin.setCredentials(credentials);
                            loggedInAdmin(admin);
                            keepRunning = false;
                        } else {
                            System.out.println("Invalid Admin credentials. Try again.");
                        }
                        break;
                    }

                    case 2: {
                        Teacher teacher = new Teacher();
                        Credentials credentials = credentialsForm("Login", "Teacher");

                        if (teacher.isCredentialsMatched(credentials)) {
                            teacher.setCredentials(credentials);
                            loggedInTeacher(teacher);
                            keepRunning = false;
                        } else {
                            System.out.println("Invalid Teacher credentials. Try again.");
                        }
                        break;
                    }

                    case 3: {
                        Student student = new Student();
                        Credentials credentials = credentialsForm("Login", "Student");

                        if (student.isCredentialsMatched(credentials)) {
                            student.setCredentials(credentials);
                            loggedInStudent(student);
                            keepRunning = false;
                        } else {
                            System.err.println("Invalid Student credentials. Try again.");
                        }
                        break;
                    }

                    case 0:
                        logOut();
                        keepRunning = false;
                        break;

                    default:
                        System.err.println("Invalid choice. Please try again.");
                        break;
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
                int choice = ConsoleUtils.adminFunctionSelector();

                switch (choice) {
                    case 0:
                        stayLoggedIn = false;
                        loginUser();
                        break;

                    case 1:
                        if (admin.makeClass(ConsoleUtils.classNameField())) {
                            System.out.println("Class added.");
                        } else {
                            System.out.println("Class already exists.");
                        }
                        break;

                    case 2:
                        System.out.println("Enter class name to remove:");
                        if (admin.removeClass(scanner.nextLine())) {
                            System.out.println("Class removed.");
                        } else {
                            System.out.println("Class does not exist.");
                        }
                        break;

                    case 3:
                        System.out.println("Enter student details to add:");
                        if (admin.addNewStudent(ConsoleUtils.studentForm())) {
                            System.out.println("Student added.");
                        } else {
                            System.err.println("Student not added.");
                        }
                        break;

                    case 4:
                        System.out.println("Enter student Class and Roll no to remove:");
                        if (admin.removeStudent(ConsoleUtils.classGradeAndRollNoForm())) {
                            System.out.println("Student removed.");
                        } else {
                            System.err.println("Student not found or not removed.");
                        }
                        break;

                    case 5:
                        System.out.println("Enter teacher details to add:");
                        // Add logic here or placeholder
                        System.out.println("Coming soon...");
                        break;

                    case 6:
                        System.out.println("Enter teacher details to remove:");
                        // Add logic here or placeholder
                        System.out.println("Coming soon...");
                        break;
                    case 7:
                        System.out.println("All available classes...");
                        System.out.println(admin.currentClasses());
                        break;
                    case 8:
                        Student student = new Student();
                        System.out.println("Enter student Class and Roll no to view:");
                        student = ConsoleUtils.classGradeAndRollNoForm();
                        System.out.println(admin.viewStudentDetail(student.getStudentAcademicInfo().getClassGrade(),student.getStudentAcademicInfo().getRollNo()));
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter valid values.");
                scanner.nextLine(); // clear buffer
            }
        }
    }
    public static void loggedInTeacher(Teacher teacher){
//        Scanner scanner = new Scanner(System.in);
//        try {
//            switch (ConsoleUtils.studentFunctionSelector()) {
//                case 1:
//                    System.out.println("Add class name to add");
//                    break;
//                case 2:
//                    System.out.println("Add class name to remove");
//                    break;
//                case 3:
//                    System.out.println("Add student details to add");
//
//                    break;
//                case 4:
//                    System.out.println("dd student details to remove");
//                    break;
//                case 5:
//                    System.out.println("Add teacher details to add");
//                    break;
//                case 6:
//                    System.out.println("Add teacher details to remove");
//                    break;
//            }
//        } catch (IllegalArgumentException e) {
//            System.err.println(e.getMessage());
//        } catch (NullPointerException e) {
//            System.err.println(e.getMessage());
//        } catch(InputMismatchException e){
//            System.err.println("Invalid input");
//        }
    }
    public static void loggedInStudent(Student student){}
    public static void logOut(){
        System.out.println("Logging out");
        System.exit(0);
    }
}