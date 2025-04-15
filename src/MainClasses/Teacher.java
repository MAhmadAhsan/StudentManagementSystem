package MainClasses;

import Details.ContactInfo;
import Details.Credentials;

public class Teacher extends Person {
    private Credentials credentials;

    // Constructor
    public Teacher(){}
    public Teacher(PersonalInfo personalInfo, ContactInfo contactInfo, Credentials Credentials) {
        super(personalInfo, contactInfo);
        setCredentials(Credentials);
    }

    // Getters
    public Credentials getCredentials() {
        if (credentials == null) {
            throw new NullPointerException("Credentials have not been initialized.");
        }
        return credentials;
    }

    //Setters
    public void setCredentials(Credentials Credentials) {
        this.credentials = Credentials;
    }

    // toString
    @Override
    public String toString() {
        return super.toString() + "\n" + getCredentials();
    }
}
