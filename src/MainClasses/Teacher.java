package MainClasses;

import Details.ContactInfo;
import Details.Credentials;

public class Teacher extends Person {

    // Constructor
    public Teacher(){}
    public Teacher(PersonalInfo personalInfo, ContactInfo contactInfo, Credentials Credentials) {
        super(personalInfo, contactInfo, Credentials);

    }


    // toString
    @Override
    public String toString() {
        return super.toString();
    }
}
