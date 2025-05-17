package PresentationLayer;
import Details.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import MainClasses.*;

public class ConsoleUtils {

    public static void title() {
        System.out.println("====================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM       ");
        System.out.println("====================================");
    }

    public static int userLoginTypeForm() {
        System.out.print("""
                    ====================================
                    Login
                    How do you want to login? Type respective number
                    1) As an Admin
                    2) As a Teacher
                    3) As a Student
                    
                    0) Exit App
                    Enter your choice (0-3):
                    """);
        int choice = -1;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try  {
                choice = scanner.nextInt();

                if (choice >= 0 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between 0 and 3.");
                    System.out.print("Enter your choice (0-3):");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Enter your choice (0-3):");
            }
        }
    }

    public static Credentials credentialsForm(String authType, String userType) {
        Credentials credentials = new Credentials();
        System.out.println("====================================");
        System.out.println(authType + " : " + userType);
        System.out.println("Enter Credentials (type '<' to go back):");

        while (true) {
            Scanner scanner = new Scanner(System.in);
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
            } catch (InputMismatchException e) {
                System.out.println("Invalid: " + e.getMessage());
            }
        }
        return credentials;
    }

    public static int adminFunctionSelector() {
        System.out.print("""
        ====================================
        What function would you like to select?
        
        Manage Classes          Manage Students                   Manage Teachers
        1) Make a new class     4) Add a new student              8) Add a new teacher
        2) Remove a class       5) Remove a student               9) Remove a teacher
        3) View all classes     6) View student info             10) View teacher detail
                                7) View all students in a        11) View all teachers
                                   class

        0) Logout
        """);

        int choice = -1;
        while (true) {
            System.out.print("Enter your choice (0-11):");
            Scanner scanner = new Scanner(System.in);
            try  {
                choice = scanner.nextInt();
                if (choice >= 0 && choice <= 11) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between 0 and 11.");
                    System.out.print("Enter your choice (0-11):");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static int teacherFunctionSelector(){
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
            """);
        int choice = -1;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Enter your choice (0-7):");
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

    public static String classGradeField() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================================");
        System.out.println("Enter Class Name (type '<' to go back):");

        while (true) {
            try {
                String className = scanner.nextLine().trim();
                if(className.equals("<")){
                    return null;
                }
                if(className.isEmpty()){
                    throw new IllegalArgumentException("Class name cannot be empty");
                }
                return className;
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid: " + e.getMessage());
            }
        }
    }

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
        StudentAcademicInfo academicInfo = studentAcademicInfoForm();
        if (academicInfo == null) {
            System.out.println("Cancelled. Returning from student form...");
            return null;
        }
        student.setStudentAcademicInfo(academicInfo);
        return student;
    }

    public static Student classGradeAndRollNoForm() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        StudentAcademicInfo academicInfo = new StudentAcademicInfo();

        System.out.println("====================================");
        System.out.println("Enter Student Info (type '<' to go back):");
        String classGrade;
        // Class Grade input
        while (true) {
            System.out.print("Enter Class Grade: ");
            classGrade = scanner.nextLine().trim();
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
            String gender = scanner.nextLine();
            if (gender.equals("<")) {
                return null;
            }
            try {
                personalInfo.setGender(gender.toUpperCase());
                return personalInfo;
            }catch (IllegalArgumentException e) {
                System.out.println("Invalid gender: " + e.getMessage());
            }
        }


    }

    public static ContactInfo contactInfoForm() {
        Scanner scanner = new Scanner(System.in);
        ContactInfo contactInfo = new ContactInfo();

        System.out.println("====================================");
        System.out.println("Enter Contact Info (type '<' to go back):");

        // Phone Number
        while (true) {
            System.out.print("Enter Phone Number (Format: 03_________ (11 characters)): ");
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
        return contactInfo;
    }

    public static StudentAcademicInfo studentAcademicInfoForm() {
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
        return studentAcademicInfo;
    }

    public static Teacher teacherInfo(){
        Scanner scanner = new Scanner(System.in);
        Teacher teacher = new Teacher();

        System.out.println("====================================");
        System.out.println("Enter teacher Details (type '<' to go back at any step)");
        // Personal Info
        PersonalInfo personalInfo = personalInfoForm();
        if (personalInfo == null) {
            return null;
        }
        teacher.setPersonalInfo(personalInfo);

        // Contact Info
        ContactInfo contactInfo = contactInfoForm();
        if (contactInfo == null) {
            return null;
        }
        teacher.setContactInfo(contactInfo);

        // Academic Info
        TeacherAcademicInfo teacherAcademicInfo = teacherAcademicInfoForm();
        if (teacherAcademicInfo == null) {
            System.out.println("Cancelled. Returning from student form...");
            return null;
        }
        teacher.setTeacherAcademicInfo(teacherAcademicInfo);
        return teacher;
    }

    public static TeacherAcademicInfo teacherAcademicInfoForm() {
        Scanner scanner = new Scanner(System.in);
        TeacherAcademicInfo teacherAcademicInfo = new TeacherAcademicInfo();

        System.out.println("====================================");
        System.out.println("Enter Academic Info (type '<' to go back):");

        // Courses taught
        while (true) {
            System.out.print("Enter Courses taught by teacher (comma-separated): ");
            String courseTaught = scanner.nextLine().trim();
            if (courseTaught.equals("<")) {
                return null;
            }
            try {
                teacherAcademicInfo.setCoursesTaught(courseTaught);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid: " + e.getMessage());
            }
        }

        // Qualification
        while (true) {
            System.out.print("Enter teacher Qualification: ");
            String qualification = scanner.nextLine().trim();
            if (qualification.equals("<")) {
                return null;
            }
            try {
                teacherAcademicInfo.setQualification(qualification);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid: " + e.getMessage());
            }
        }
        return teacherAcademicInfo;
    }

    public static String courseNameField() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course Name (type '<' to go back):");
        String courseName;
        while (true) {
            try {
                courseName = scanner.nextLine().trim();
                if(courseName.equals("<")){
                    return null;
                }
                if(courseName.isEmpty()){
                    throw new IllegalArgumentException("Course name cannot be empty");
                }
                return courseName;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid : " + e.getMessage());
            }
        }
    }

    public static String dateField() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date (format: dd-MM-yyyy, type '<' to go back):");
        String date;
        while (true) {
            try {
                date = scanner.nextLine().trim();
                if (date.equals("<")) {
                    return null;
                }
                if (date.isEmpty()) {
                    throw new IllegalArgumentException("Date cannot be empty");
                }

                // Validate date format: dd-MM-yyyy
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false); // strictly validate the format
                sdf.parse(date); // throws ParseException if the format is wrong

                return date; // Valid date

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid: " + e.getMessage());
            } catch (ParseException e) {
                System.out.println("Invalid: Please enter the date in the format dd-MM-yyyy.");
            }
        }
    }

    public static String classActivityField() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter class activity Name (type '<' to go back):");
        String classActivityName;
        while (true) {
            try {
                classActivityName = scanner.nextLine().trim();
                if(classActivityName.equals("<")){
                    return null;
                }
                if(classActivityName.isEmpty()){
                    throw new IllegalArgumentException("Class activity name cannot be empty");
                }
                return classActivityName;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid : " + e.getMessage());
            }
        }
    }

    public static Map<String, String> uploadAttendanceForm(String studentsInClass, String classGrade, String courseName, String date) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> attendance = new HashMap<>();

        // Split student roll numbers using newline character
        String[] studentList = studentsInClass.split("\\n");

        System.out.println("\nTaking attendance for class: " + classGrade + ", Course: " + courseName + ", Date: " + date);

        for (String roll : studentList) {
            roll = roll.trim();
            if (!roll.isEmpty()) {
                String status;
                while (true) {
                    System.out.print("Enter attendance for roll number " + roll + " (P/A): ");
                    status = scanner.nextLine().trim();
                    if (status.equalsIgnoreCase("P") || status.equalsIgnoreCase("A")) {
                        // Normalize to consistent case
                        status = Character.toUpperCase(status.charAt(0)) + status.substring(1).toLowerCase();
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter 'Present' or 'Absent'.");
                    }
                }
                attendance.put(roll, status);
            }
        }
        return attendance;
    }

    public static Map<String, String> uploadGradesForm(String studentsInClass, String classGrade, String courseName, String activity) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> grades = new HashMap<>();

        // Split student roll numbers using newline character
        String[] studentList = studentsInClass.split("\\n");

        System.out.println("\nUpload attendance for class: " + classGrade + ", Course: " + courseName + ", Activity: " + activity);

        // Manual entry per student
        for (String roll : studentList) {
            roll = roll.trim();
            if (!roll.isEmpty()) {
                String grade;
                while (true) {
                    System.out.print("Enter Grades for roll number " + roll + " (A,B,C,D,F): ");
                    grade = scanner.nextLine().trim();
                    if (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("F")) {
                        // Normalize to consistent case
                        grade = Character.toUpperCase(grade.charAt(0)) + grade.substring(1).toLowerCase();
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter one of the following grades: A,B,C,D,F.");
                    }
                }
                grades.put(roll, grade);
            }
        }
        return grades;
    }
}