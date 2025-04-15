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
        Credentials credentials = new Credentials();
        switch (userLoginTypeForm()){
            case 1:
                Admin admin = new Admin();
                credentials = credentialsForm("Login","Admin");
                if(admin.isCredentialsMatched(credentials)) {
                    admin.setCredentials(credentials);
                    loggedInAdmin(admin);
                }
                break;

            case 2:
                Teacher teacher = new Teacher();
                credentials = credentialsForm("Login","Teacher");
                if(teacher.isCredentialsMatched(credentials)) {
                    teacher.setCredentials(credentials);
                    loggedInTeacher(teacher);
                }
                break;
            case 3:
                Student student = new Student();
                credentials = credentialsForm("Login","Student");
                if(student.isCredentialsMatched(credentials)) {
                    student.setCredentials(credentials);
                    loggedInStudent(student);
                }
                break;
        }
    }

    public static void loggedInAdmin(Admin admin){
        Scanner scanner = new Scanner(System.in);
            try {
                switch (ConsoleUtils.adminFunctionSelector()) {
                    case 1:
                            System.out.println("Add class name to add");
                            admin.makeClass(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Add class name to remove");
                        admin.removeClass(scanner.nextLine());
                        break;
                    case 3:
                        System.out.println("Add student details to add");
                        if(admin.addNewStudent(ConsoleUtils.studentForm())){
                            System.out.println("Student added.");
                        }else{
                            System.err.println("Student not added.");
                        }

                        break;
                    case 4:
                        System.out.println("dd student details to remove");
                        break;
                    case 5:
                        System.out.println("Add teacher details to add");
                        break;
                    case 6:
                        System.out.println("Add teacher details to remove");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            } catch (NullPointerException e) {
                System.err.println(e.getMessage());
            } catch(InputMismatchException e){
                System.err.println("Invalid input");
            }
    }
    public static void loggedInTeacher(Teacher teacher){}
    public static void loggedInStudent(Student student){}
}