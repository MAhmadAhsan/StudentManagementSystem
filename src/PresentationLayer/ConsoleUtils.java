package PresentationLayer;
import Details.*;
import java.util.Scanner;
import MainClasses.*;

public class ConsoleUtils {

    public static void title() {
        System.out.println("====================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM       ");
        System.out.println("====================================");
    }
    public static int userLoginTypeForm() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (true) {
            try {
                System.out.print("====================================\n" +
                        "Login\n" +
                        "How do you want to login? Type respective number\n" +
                        "1) As an Admin\n" +
                        "2) As a Student\n" +
                        "3) As a Teacher\n" +
                        "0) Exit App\n" +
                        "Enter your choice (0-3): ");

                String input = scanner.nextLine(); // Read full line to avoid scanner issues
                choice = Integer.parseInt(input);

                if (choice >= 0 && choice <= 3) {
                    return choice;
                } else {
                    System.err.println("Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a number.");
            }
        }
    }
    public static int adminFunctionSelector() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (true) {
            try {
                System.out.print("====================================\n" +
                                "Select Function\n" +
                                "What function would you like to select?\n" +
                                "1) Make a new class\n" +
                                "2) Remove a current class\n" +
                                "3) Add a new Student\n" +
                                "4) Remove a current Student\n" +
                                "5) Add a new Teacher\n" +
                                "6) Remove a Teacher\n" +
                                "7) See all available classes\n" +
                                "8) View Student Detail\n" +
                                "0) Logout\n" +
                                "Enter your choice (0-8): ");

                String input = scanner.nextLine();
                choice = Integer.parseInt(input);

                if (choice >= 0 && choice <= 8) {
                    return choice; // Return the user's choice, including 0
                } else {
                    System.err.println("Please enter a number between 0 and 6.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a number.");
            }
        }
    }
    public static Credentials credentialsForm(String authType, String userType) {
        Scanner scanner = new Scanner(System.in);
        Credentials credentials = new Credentials();

        System.out.println(authType + " as " + userType);
        System.out.println("====================================");
        System.out.println("Enter Credentials (type '<' to go back):");

        while (true) {
            try {
                System.out.println("Enter Username  : ");
                String username = scanner.nextLine().trim();
                if(username.equals("<")){
                    return null;
                }
                credentials.setUsername(username);
                System.out.println("Enter Password  : ");
                String password = scanner.nextLine().trim();
                if(password.equals("<")){
                    return null;
                }
                credentials.setPassword(password);
                break;
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid: " + e.getMessage());
            }
        }

        System.out.println("====================================");
        return credentials;
    }
    public static String classNameField() {
        Scanner scanner = new Scanner(System.in);
        Credentials credentials = new Credentials();
        System.out.println("====================================");
        System.out.println("Enter Class Name (type '<' to go back):");

        while (true) {
            try {
                String className = scanner.nextLine().trim();
                if(className.equals("<")){
                    return null;
                }
                return className;
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid Class Name: " + e.getMessage());
            }
        }
    }
    public static Student studentForm() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();

        System.out.println("====================================");
        System.out.println("Enter Student Details (type '<' to go back at any step)");

        // Personal Info
        PersonalInfo personalInfo = personalInfoForm();
        if (personalInfo == null) {
            return null;
        }
        student.setPersonalInfo(personalInfo);

        // Contact Info
        ContactInfo contactInfo = contactInfoForm();
        if (contactInfo == null) {
            return null;
        }
        student.setContactInfo(contactInfo);

        // Academic Info
        StudentAcademicInfo academicInfo = academicInfoForm();
        if (academicInfo == null) {
            System.out.println("Cancelled. Returning from student form...");
            return null;
        }
        student.setStudentAcademicInfo(academicInfo);
        System.out.println("====================================");
        return student;
    }
    public static Student classGradeAndRollNoForm() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        StudentAcademicInfo academicInfo = new StudentAcademicInfo();

        System.out.println("====================================");
        System.out.println("Enter Student Info (type '<' to go back):");

        // Class Grade input
        while (true) {
            System.out.print("Enter Class Grade: ");
            String classGrade = scanner.nextLine().trim();
            if (classGrade.equals("<")) {
                System.out.println("Going back...");
                return null;
            }
            try {
                academicInfo.setClassGrade(classGrade);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid class grade: " + e.getMessage());
            }
        }

        // Roll No input
        while (true) {
            System.out.print("Enter Roll No: ");
            String rollNo = scanner.nextLine().trim();
            if (rollNo.equals("<")) {
                System.out.println("Going back...");
                return null;
            }
            try {
                academicInfo.setRollNo(rollNo);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid roll number: " + e.getMessage());
            }
        }

        student.setStudentAcademicInfo(academicInfo);
        return student;
}
    public static PersonalInfo personalInfoForm() {
        Scanner scanner = new Scanner(System.in);
        PersonalInfo personalInfo = new PersonalInfo();

        System.out.println("====================================");

        // Name input
        while (true) {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();
            if (name.equals("<")) {
                return null;
            }
            try {
                personalInfo.setName(name);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid name: " + e.getMessage());
            }
        }

        // Age input
        while (true) {
            System.out.print("Enter Age: ");
            String input = scanner.nextLine().trim();
            if (input.equals("<")) {
                System.out.println("Going back...");
                return null;
            }
            try {
                int age = Integer.parseInt(input);
                personalInfo.setAge(age);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for age.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid age: " + e.getMessage());
            }
        }

        // Gender input
        while (true) {
            System.out.print("Enter Gender (M/F): ");
            String gender = scanner.nextLine().trim();
            if (gender.equals("<")) {
                System.out.println("Going back...");
                return null;
            }
            if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")) {
                personalInfo.setGender(gender.toUpperCase());
                break;
            } else {
                System.out.println("Invalid gender. Please enter 'M' or 'F'.");
            }
        }

        System.out.println("====================================");
        return personalInfo;
    }
    public static ContactInfo contactInfoForm() {
        Scanner scanner = new Scanner(System.in);
        ContactInfo contactInfo = new ContactInfo();

        System.out.println("====================================");
        System.out.println("Enter Contact Info (type '<' to go back):");

        // Phone Number
        while (true) {
            System.out.print("Enter Phone Number (Format: 03XXXXXXXXX): ");
            String phone = scanner.nextLine().trim();
            if (phone.equals("<")) {
                System.out.println("Going back...");
                return null;
            }
            try {
                contactInfo.setPhoneNumber(phone);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid phone number: " + e.getMessage());
            }
        }

        // Address
        while (true) {
            System.out.print("Enter Address: ");
            String address = scanner.nextLine().trim();
            if (address.equals("<")) {
                System.out.println("Going back...");
                return null;
            }
            try {
                contactInfo.setAddress(address);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid address: " + e.getMessage());
            }
        }

        System.out.println("====================================");
        return contactInfo;
    }
    public static StudentAcademicInfo academicInfoForm() {
        Scanner scanner = new Scanner(System.in);
        StudentAcademicInfo studentAcademicInfo = new StudentAcademicInfo();

        System.out.println("====================================");
        System.out.println("Enter Academic Info (type '<' to go back):");

        // Roll No
        while (true) {
            System.out.print("Enter Roll No: ");
            String rollNo = scanner.nextLine().trim();
            if (rollNo.equals("<")) {
                System.out.println("Going back...");
                return null;
            }
            try {
                studentAcademicInfo.setRollNo(rollNo);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Roll No: " + e.getMessage());
            }
        }

        // Class Grade
        while (true) {
            System.out.print("Enter Class Grade: ");
            String classGrade = scanner.nextLine().trim();
            if (classGrade.equals("<")) {
                System.out.println("Going back...");
                return null;
            }
            try {
                studentAcademicInfo.setClassGrade(classGrade);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Class Grade: " + e.getMessage());
            }
        }

        // Courses
        while (true) {
            System.out.print("Enter Courses (comma-separated): ");
            String courses = scanner.nextLine().trim();
            if (courses.equals("<")) {
                System.out.println("Going back...");
                return null;
            }
            try {
                studentAcademicInfo.setCourses(courses);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid courses input: " + e.getMessage());
            }
        }

        System.out.println("====================================");
        return studentAcademicInfo;
    }
}