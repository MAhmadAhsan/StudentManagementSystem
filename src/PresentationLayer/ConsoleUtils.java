package PresentationLayer;
import Details.*;
import java.util.Scanner;
import MainClasses.*;

public class ConsoleUtils {
    /**
     * Displays the title banner for the Student Management System.
     * <p>
     * This method prints a decorative header to the console for visual emphasis
     * when the application starts or when displaying major sections.
     */
    public static void title() {
        System.out.println("====================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM       ");
        System.out.println("====================================");
    }

    /**
     * Displays a login menu and prompts the user to select a login type.
     * <p>
     * The user is presented with options to log in as an Admin, Teacher, or Student,
     * or to exit the application. Input is validated to ensure it's a number between 0 and 3.
     *
     * @return an integer representing the selected login type:
     *         1 for Admin, 2 for Teacher, 3 for Student, 0 to Exit
     */
    public static int userLoginTypeForm() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        System.out.print("""
                    ====================================
                    Login
                    How do you want to login? Type respective number
                    1) As an Admin
                    2) As a Teacher
                    3) As a Student
                    
                    0) Exit App
                    Enter your choice (0-3):\s""");
        while (true) {
            try {
                String input = scanner.nextLine(); // Read full line to avoid scanner issues
                choice = Integer.parseInt(input);

                if (choice >= 0 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between 1 and 4.");
                    System.out.print("Enter your choice (0-3):");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Enter your choice (0-3):");
            }
        }
    }

    /**
     * Prompts the user to enter login credentials based on the given authentication and user type.
     * <p>
     * The method displays a header with the provided auth and user type, and then collects a username and password.
     * Users can type '<' at any prompt to go back, in which case the method returns {@code null}.
     * Input is trimmed and basic validation is handled through the {@link Credentials} setters.
     *
     * @param authType the type of authentication (e.g., "Login" or "Sign In")
     * @param userType the user role (e.g., "Admin", "Teacher", "Student")
     * @return a {@link Credentials} object containing the entered username and password,
     *         or {@code null} if the user chooses to go back
     */
    public static Credentials credentialsForm(String authType, String userType) {
        Credentials credentials = new Credentials();
        Scanner scanner = new Scanner(System.in);
        System.out.println(authType + " : " + userType);
        System.out.println("====================================");
        System.out.println("Enter Credentials (type '<' to go back):");

        while (true) {
            try  {
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

    /**
     * Displays a menu of administrative functions and prompts the admin to select one.
     * <p>
     * The menu includes options for managing classes, students, and teachers,
     * such as adding/removing entities, viewing information, and recovering credentials.
     * The input is validated to ensure a number between 0 and 13 is entered.
     *
     * @return an integer representing the selected function:
     *         values 1–13 correspond to specific actions, 0 indicates logout
     */
//    public static int adminFunctionSelector() {
//        Scanner scanner = new Scanner(System.in);
//        int choice = -1;
//        System.out.print("""
//            ====================================
//            What function would you like to select?
//
//            Manage Classes
//            1) Make a new class
//            2) Remove a current class
//            3) View all created classes
//
//            Manage Students
//            4) Add a new student
//            5) Remove a current student
//            6) View student Info
//            7) Recover student credentials
//            8) View All Students in a class
//
//            Manage Teachers
//            9) Add a new teacher
//            10) Remove a teacher
//            11) View teacher detail
//            12) Recover teacher credentials
//            13) View all teachers
//
//            0) Logout
//            Enter your choice (0-13):\s""");
//
//        while (true) {
//            try {
//                String input = scanner.nextLine();
//                choice = Integer.parseInt(input);
//
//                if (choice >= 0 && choice <= 13) {
//                    return choice; // Return the user's choice, including 0
//                } else {
//                    System.out.println("Please enter a number between 0 and 13.");
//                    System.out.print("Enter your choice (0-13):");
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input. Please enter a number.");
//                System.out.print("Enter your choice (0-13):");
//            }
//        }
//    }
    public static int adminFunctionSelector() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        System.out.print("""
        ====================================
        What function would you like to select?
        
        Manage Classes          Manage Students                   Manage Teachers
        1) Make a new class     4) Add a new student              9) Add a new teacher
        2) Remove a class       5) Remove a student              10) Remove a teacher
        3) View all classes     6) View student info             11) View teacher detail
                                7) Recover student credentials   12) Recover teacher credentials
                                8) View all students in a        13) View all teachers
                                   class

        0) Logout
        Enter your choice (0-13):\s""");

        while (true) {
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);

                if (choice >= 0 && choice <= 13) {
                    return choice; // Return the user's choice, including 0
                } else {
                    System.out.println("Please enter a number between 0 and 13.");
                    System.out.print("Enter your choice (0-13):");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Enter your choice (0-13):");
            }
        }
    }

    /**
     * Displays a menu of teacher functions and prompts the teacher to select one.
     * <p>
     * The menu includes options to view student and class information, manage attendance and grades,
     * and log out. The input is validated to ensure it's a number between 0 and 7.
     *
     * @return an integer representing the selected function:
     *         values 1–7 correspond to specific actions, 0 indicates logout
     */
    public static int teacherFunctionSelector(){
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        System.out.print("""
            ====================================
            What function would you like to select?
            
            Manage Students
            1) View student Info
            2) View All Students in a class
            3) View all created classes
            4) Upload class Attendance
            5) View class Attendance
            6) Upload class Grades
            7) View class Grades
            
            0) Logout
            Enter your choice (0-7):\s""");

        while (true) {
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);

                if (choice >= 0 && choice <= 7) {
                    return choice; // Return the user's choice, including 0
                } else {
                    System.out.println("Please enter a number between 0 and 11.");
                    System.out.print("Enter your choice (0-7):");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Enter your choice (0-7):");
            }
        }
    }


    /**
     * Displays a menu of student functions and prompts the student to select one.
     * <p>
     * The menu includes options to view personal information, attendance, and grades.
     * The input is validated to ensure a number between 0 and 3 is entered.
     *
     * @return an integer representing the selected function:
     *         1 for viewing info, 2 for attendance, 3 for grades, 0 to logout
     */
    public static int studentFunctionSelector() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        System.out.print("""
            ====================================
            What function would you like to select?
            
            Student Functions
            1) View Info
            2) View Attendance
            3) View Grades
            
            0) Logout
            Enter your choice (0-3):\s""");

        while (true) {
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);

                if (choice >= 0 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between 0 and 3.");
                    System.out.print("Enter your choice (0-3):");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Enter your choice (0-3):");
            }
        }
    }

    /**
     * Prompts the user to enter a class name or grade.
     * <p>
     * The user can type '<' to go back, in which case the method returns {@code null}.
     * The input is trimmed before being returned. If an {@link IllegalArgumentException} is caught,
     * the method also returns {@code null}.
     *
     * @return the entered class name as a {@link String}, or {@code null} if the user chooses to go back or an error occurs
     */
    public static String classGradeField() {
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
                return null;
            }
        }
    }

    /**
     * Collects and assembles student information from the user through the console.
     * <p>
     * This method prompts the user to enter personal, contact, and academic information for a student.
     * At any stage, the user can type '<' to cancel the process and return {@code null}.
     * If all inputs are successfully collected, a {@link Student} object is created and returned.
     *
     * @return a fully populated {@link Student} object, or {@code null} if the process is canceled at any step
     */
    public static Student studentInfoForm() {
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

    /**
     * Collects class grade and roll number input from the user via console.
     * <p>
     * This method prompts the user to enter a class grade and a roll number for a student.
     * Input is validated using the setters of {@link StudentAcademicInfo}.
     * The user can type '<' at any point to cancel the process, in which case {@code null} is returned.
     *
     * @return a {@link Student} object with academic info set, or {@code null} if the user cancels the input
     */
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

    /**
     * Collects personal information from the user via console input.
     * <p>
     * Prompts the user to enter the student's name, age, and gender.
     * Each input is validated, and the user may type '<' at any point to cancel the process.
     * If canceled, the method returns {@code null}; otherwise, it returns a populated {@link PersonalInfo} object.
     *
     * @return a {@link PersonalInfo} object with the entered data, or {@code null} if the user cancels the input
     */
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
            String gender = scanner.nextLine();
            if (gender.equals("<")) {
                return null;
            }
            try {
                personalInfo.setGender(gender.toUpperCase());
                System.out.println("====================================");
                return personalInfo;
            }catch (IllegalArgumentException e) {
                System.out.println("Invalid gender: " + e.getMessage());
            }
        }


    }

    /**
     * Collects contact information from the user via console input.
     * <p>
     * Prompts the user to enter the student's phone number (in a specific format) and address.
     * The user may type '<' at any point to cancel the process and return {@code null}.
     * If the input is valid, it returns a {@link ContactInfo} object with the entered data.
     *
     * @return a {@link ContactInfo} object with the entered contact details, or {@code null} if the user cancels the input
     */
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

    /**
     * Collects academic information from the user via console input.
     * <p>
     * Prompts the user to enter the student's roll number, class grade, and courses (comma-separated).
     * The user may type '<' at any point to cancel the process and return {@code null}.
     * Each input is validated, and if all inputs are valid, a {@link StudentAcademicInfo} object is returned.
     *
     * @return a {@link StudentAcademicInfo} object with the entered academic details, or {@code null} if the user cancels the input
     */
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