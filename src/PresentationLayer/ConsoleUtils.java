package PresentationLayer;
import DataAcessLayer.StudentData;
import Details.AcademicInfo;
import Details.ContactInfo;
import Details.Credentials;
import java.util.Scanner;
import Functions.*;
import MainClasses.Admin;
import MainClasses.PersonalInfo;
import MainClasses.Student;
import MainClasses.Teacher;

public class ConsoleUtils {

    public static void title() {
        System.out.println("=====================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM       ");
        System.out.println("=====================================");
    }

    public static void addLine() {
        System.out.println();
    }

    public static void addLine(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }
    public static Admin registrationForm(Admin admin){
        do{
            try {
                admin.setCredentials(credentialsForm("Register", "Admin"));
                admin.register(admin.getCredentials());
                System.out.println("Admin registered.");
            }catch (IllegalArgumentException e){
                System.err.println(e.getMessage());
            }catch (NullPointerException e){
                System.err.println(e.getMessage());
            }
        }while(!admin.isRegistered());
        return admin;
    }

    public static int userLoginTypeForm() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean validChoice = false;
        do {
            System.out.println("====================================");
            System.out.println("Login");
            System.out.println("How do you want to login? Type respective number");
            System.out.println("1) As an Admin\n2) As a Teacher\n3) As a Student");
            choice = Input.intRangeInput(scanner.nextInt(), 1, 3);
            scanner.nextLine();
            validChoice = true;
        } while (!validChoice);

        return choice;
    }

    public static int adminFunctionSelector() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean validChoice = false;
        do {
            System.out.println("====================================");
            System.out.println("Select function");
            System.out.println("What function would you like to select?");
            System.out.println("1) Make a new class\n2) Remove a current class\n3) Add a new Student\n4) Remove a current Student\n5) Add a new Teacher\n6) Remove a Teacher");
            choice = Input.intRangeInput(scanner.nextInt(), 1, 6);
            scanner.nextLine();
            validChoice = true;
        } while (!validChoice);
        return choice;
    }

    public static Credentials credentialsForm(String authType, String userType) {
        Scanner scanner = new Scanner(System.in);
        Credentials credentials = new Credentials();
        System.out.println(authType + " as " + userType);
        System.out.println("====================================");
        boolean validChoice = true;
        do {
            System.out.println("Enter Username  : ");
            credentials.setUsername(scanner.nextLine());
        } while (credentials.getUsername() == null);

        do {
            System.out.println("Enter Password: ");
            credentials.setPassword(scanner.nextLine());
        } while (credentials.getPassword() == null);
        System.out.println("====================================");
        return credentials;
    }

    public static Student studentForm() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        System.out.println("====================================");
        System.out.println("Enter Student Details");
        student.setPersonalInfo(personalInfoForm());
        student.setContactInfo(contactInfoForm());
        student.setAcademicInfo(academicInfoForm());
        return student;
    }

    public static PersonalInfo personalInfoForm() {
        Scanner scanner = new Scanner(System.in);
        PersonalInfo personalInfo = new PersonalInfo();
        System.out.println("====================================");
        System.out.println("Enter Personal Info:");
        do {
            System.out.println("Enter Name: ");
            personalInfo.setName(scanner.nextLine());
        } while (personalInfo.getName() == null);

        do {
            System.out.println("Enter age: ");
            personalInfo.setAge(scanner.nextInt());
            scanner.nextLine();
        } while (personalInfo.getAge() == 0);

        do {
            System.out.println("Enter Gender M for male and F for female: ");
            String gender = scanner.next();
            if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")) {
                personalInfo.setGender(gender);
            }else{
                System.out.println("Invalid gender");
            }
        } while (personalInfo.getGender() == null);

        System.out.println("====================================");
        return personalInfo;
    }
    public static ContactInfo contactInfoForm(){
        Scanner scanner = new Scanner(System.in);
        ContactInfo contactInfo = new ContactInfo();
        System.out.println("====================================");
        System.out.println("Enter Contact Info:");
        do {
            System.out.println("Enter Phone Number(Format: 03_________): ");
            contactInfo.setPhoneNumber(scanner.nextLine());
        } while (contactInfo.getPhoneNumber() == null);

        do {
            System.out.println("Enter Address: ");
            contactInfo.setAddress(scanner.nextLine());
        } while (contactInfo.getAddress() == null);

        System.out.println("====================================");
        return contactInfo;
    }
    public static AcademicInfo academicInfoForm(){

        Scanner scanner = new Scanner(System.in);
        AcademicInfo academicInfo = new AcademicInfo();
        System.out.println("====================================");
        System.out.println("Enter Academic Info:");

        do {
            System.out.println("Enter Roll No: ");
            academicInfo.setRollNo(scanner.nextLine());
        } while (academicInfo.getRollNo() == null);

        do {
            System.out.println("Enter Class Grade: ");
            academicInfo.setClassGrade(scanner.nextLine());
        } while (academicInfo.getClassGrade() == null);

        do{
            System.out.println("Enter Courses: (Enter courses by comma separated): )");
            academicInfo.setCourses(scanner.nextLine());
        }while (academicInfo.getCourses() == null);
        System.out.println("====================================");
        return academicInfo;
    }
}